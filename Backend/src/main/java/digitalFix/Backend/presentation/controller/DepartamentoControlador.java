package digitalFix.Backend.presentation.controller;

import digitalFix.Backend.persistence.entity.Departamento;
import digitalFix.Backend.persistence.entity.Trabajador;
import digitalFix.Backend.service.implementation.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
@CrossOrigin("http://localhost:5173")
public class DepartamentoControlador {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<Departamento> listarDepartamentos(){
        return departamentoService.listarDepartamentos();
    }

    @PostMapping
    public ResponseEntity<?> agregarDepartamento(@RequestBody Departamento departamento){

        departamentoService.agregarDepartamento(departamento);
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editarDepartamento(@RequestBody Departamento departamento){
        departamentoService.agregarDepartamento(departamento);
        return new ResponseEntity<>("Editado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id){

        departamentoService.eliminarDepartamento(id);
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }
}
