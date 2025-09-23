package digitalFix.Backend.service.implementation;

import digitalFix.Backend.persistence.entity.Departamento;
import digitalFix.Backend.persistence.repository.DepartamentoRepositorio;
import digitalFix.Backend.service.interfaces.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @Override
    public List<Departamento> listarDepartamentos() {
        return departamentoRepositorio.findAll();
    }

    @Override
    public Departamento buscarDepartamento(Long id) {
        Departamento departamento = departamentoRepositorio.findById(id).orElse(null);

        return departamento;
    }

    @Override
    public void agregarDepartamento(Departamento departamento) {

        departamentoRepositorio.save(departamento);
    }

    @Override
    public void eliminarDepartamento(Long id) {

        departamentoRepositorio.deleteById(id);
    }
}
