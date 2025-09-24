package digitalFix.Backend.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"usuario", "mensaje", "estado", "token"})
public record AuthResponse(
        String usuario,
        String mensaje,
        String token,
        Boolean estado) {
}
