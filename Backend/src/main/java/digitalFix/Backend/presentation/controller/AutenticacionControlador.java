package digitalFix.Backend.presentation.controller;

import digitalFix.Backend.presentation.DTO.AuthCrearUsuarioRequest;
import digitalFix.Backend.presentation.DTO.AuthInicioRequest;
import digitalFix.Backend.presentation.DTO.AuthResponse;
import digitalFix.Backend.service.implementation.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:5173")
public class AutenticacionControlador {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody AuthCrearUsuarioRequest usuarioRequest ){

        return new ResponseEntity<>(this.userDetailService.crearUsuario(usuarioRequest), HttpStatus.CREATED);
    }

    @PostMapping("/inicio")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthInicioRequest usuarioRequest){

        return new ResponseEntity<>(this.userDetailService.iniciarUsuario(usuarioRequest), HttpStatus.OK);
    }
}
