package digitalFix.Backend.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.clave.privada}")
    private String clavePrivada;

    @Value("${security.jwt.usuario.generador}")
    private String usuarioGenerador;

    public String crearToken(Authentication authentication){

        Algorithm algorithm = Algorithm.HMAC256(this.clavePrivada);

        String usuario = authentication.getPrincipal().toString();

        String permisos = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = JWT.create()
                .withIssuer(usuarioGenerador)
                .withSubject(usuario)
                .withClaim("permisos", permisos)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return token;
    }

    public DecodedJWT validarToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.clavePrivada);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.usuarioGenerador)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;

        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido");
        }
    }

    public String extraerUsuario(DecodedJWT decodedJWT){

        return decodedJWT.getSubject().toString();
    }

    public Claim getClaimEspecifico(DecodedJWT decodedJWT, String claimNombre){

        return decodedJWT.getClaim(claimNombre);
    }

    public Map<String, Claim> getClaims(DecodedJWT decodedJWT){

        return decodedJWT.getClaims();
    }
}
