package digitalFix.Backend.presentation.controller;

import digitalFix.Backend.persistence.entity.Trabajador;
import digitalFix.Backend.service.implementation.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin("http://localhost:5173")
public class TrabajadorControlador {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    public List<Trabajador> listarTrabajadores(){
        return trabajadorService.listarTrabajadores();
    }

    @PostMapping
    public ResponseEntity<?> agregarTrabajador(@RequestBody Trabajador trabajador){

        trabajadorService.agregarTrabajador(trabajador);
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editarTrabajador(@RequestBody Trabajador trabajador){
        trabajadorService.agregarTrabajador(trabajador);
        return new ResponseEntity<>("Editado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTrabajador(@PathVariable Long id){

        trabajadorService.eliminarTrabajador(id);
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }
}
