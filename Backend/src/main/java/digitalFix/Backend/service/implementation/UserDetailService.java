package digitalFix.Backend.service.implementation;

import digitalFix.Backend.Util.JwtUtils;
import digitalFix.Backend.persistence.entity.Rol;
import digitalFix.Backend.persistence.entity.Usuario;
import digitalFix.Backend.persistence.repository.RolRepositorio;
import digitalFix.Backend.persistence.repository.UsuarioRepositorio;
import digitalFix.Backend.presentation.DTO.AuthCrearUsuarioRequest;
import digitalFix.Backend.presentation.DTO.AuthInicioRequest;
import digitalFix.Backend.presentation.DTO.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findUsuarioByNombreUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<SimpleGrantedAuthority> permisosLista = new ArrayList<>();

        usuario.getRoles()
                .forEach(role -> permisosLista.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));

        return new User(usuario.getNombreUsuario(),
                usuario.getContraseña(),
                usuario.getActivo(),
                usuario.getNoExpirada(),
                usuario.getNoBloqueado(),
                usuario.getCredencialesNoExpiradas(),
                permisosLista
                );
    }

    public AuthResponse crearUsuario(AuthCrearUsuarioRequest crearRolRequest){

        String nombreUsuario = crearRolRequest.usuario();
        String contraseña = crearRolRequest.contraseña();
        List<String> rolesRequest = crearRolRequest.rolRequest().rolLista();

        Set<Rol> rolLista = rolRepositorio.findRolByRolEnumIn(rolesRequest).stream().collect(Collectors.toSet());

        if(rolLista.isEmpty()){
            throw new IllegalArgumentException("Rol no definido");
        }

        Usuario usuario = Usuario.builder()
                .nombreUsuario(nombreUsuario)
                .contraseña(passwordEncoder.encode(contraseña))
                .roles(rolLista)
                .activo(true)
                .noBloqueado(true)
                .noExpirada(true)
                .credencialesNoExpiradas(true)
                .build();

        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        ArrayList<SimpleGrantedAuthority> permisos = new ArrayList<>();

        usuarioGuardado.getRoles()
                .forEach(rol -> permisos.add(new SimpleGrantedAuthority(("ROLE_".concat(rol.getRolEnum().name())))));


        SecurityContext securityContextholder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioGuardado, null, permisos);

        String token = jwtUtils.crearToken(authentication);
        AuthResponse response = new AuthResponse(nombreUsuario, "Creado", token, true);

        return response;
    }

    public Authentication autenticar(String nombreUsuario, String contraseña){
        UserDetails userDetails = this.loadUserByUsername(nombreUsuario);

        if(userDetails == null){
            throw new BadCredentialsException("Datos incorrectos");
        }
        if(!passwordEncoder.matches(contraseña, userDetails.getPassword())){
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        return new UsernamePasswordAuthenticationToken(nombreUsuario, contraseña, userDetails.getAuthorities());
    }

    public AuthResponse iniciarUsuario(AuthInicioRequest authInicioRequest){
        String nombreUsuario = authInicioRequest.usuario();
        String contraseña = authInicioRequest.contraseña();

        Authentication authentication = this.autenticar(nombreUsuario, contraseña);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.crearToken(authentication);
        AuthResponse authResponse = new AuthResponse(nombreUsuario, "Iniciado correctamente", token, true);
        return authResponse;
    }
}
