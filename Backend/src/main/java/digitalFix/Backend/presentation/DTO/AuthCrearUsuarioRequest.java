package digitalFix.Backend.presentation.DTO;

public record AuthCrearUsuarioRequest(String usuario,
                                      String contraseña,
                                      AuthCrearRolRequest rolRequest) {
}
