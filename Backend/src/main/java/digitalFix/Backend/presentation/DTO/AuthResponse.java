package digitalFix.Backend.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"usuario", "mensaje", "estado", "jwt"})
public record AuthResponse(
        String usuario,
        String mensaje,
        String jwt,
        Boolean estado) {
}
