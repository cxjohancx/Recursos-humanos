package digitalFix.Backend.configuration.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import digitalFix.Backend.Util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {


    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull  FilterChain filterChain) throws ServletException, IOException {


        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token != null){
            token = token.substring(7);

            DecodedJWT jwtDecodificado = jwtUtils.validarToken(token);

            String usuario = jwtUtils.extraerUsuario(jwtDecodificado);
            String stringPermisos = jwtUtils.getClaimEspecifico(jwtDecodificado, "permisos").asString();

            Collection<? extends GrantedAuthority> permisos = AuthorityUtils.commaSeparatedStringToAuthorityList(stringPermisos);

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication autenticacion = new UsernamePasswordAuthenticationToken(usuario, null, permisos);
            context.setAuthentication(autenticacion);

            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }
}
