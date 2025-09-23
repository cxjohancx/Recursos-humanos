package digitalFix.Backend.service.implementation;

import digitalFix.Backend.persistence.entity.Trabajador;
import digitalFix.Backend.persistence.repository.TrabajadorRepositorio;
import digitalFix.Backend.service.interfaces.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService implements ITrabajadorService {

    @Autowired
    private TrabajadorRepositorio trabajadorRepositorio;

    @Override
    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepositorio.findAll();
    }

    @Override
    public Trabajador buscarTrabajador(Long id) {
        Trabajador trabajador = trabajadorRepositorio.findById(id).orElse(null);

        return trabajador;
    }

    @Override
    public void agregarTrabajador(Trabajador trabajador) {

        trabajadorRepositorio.save(trabajador);
    }

    @Override
    public void eliminarTrabajador(Long id) {

        trabajadorRepositorio.deleteById(id);
    }
}
