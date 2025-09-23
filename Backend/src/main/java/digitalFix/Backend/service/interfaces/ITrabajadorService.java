package digitalFix.Backend.service.interfaces;

import digitalFix.Backend.persistence.entity.Trabajador;

import java.util.List;

public interface ITrabajadorService {

    List<Trabajador> listarTrabajadores();
    Trabajador buscarTrabajador(Long id);
    void agregarTrabajador(Trabajador trabajador);
    void eliminarTrabajador(Long id);
}