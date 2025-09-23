package digitalFix.Backend.service.interfaces;

import digitalFix.Backend.persistence.entity.Departamento;

import java.util.List;

public interface IDepartamentoService {

    List<Departamento> listarDepartamentos();
    Departamento buscarDepartamento(Long id);
    void agregarDepartamento(Departamento departamento);
    void eliminarDepartamento(Long id);
}
