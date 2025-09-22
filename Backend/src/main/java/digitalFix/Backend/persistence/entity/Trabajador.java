package digitalFix.Backend.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trabajador {

    @Id
    private Long id;

    private String nombre;
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;
}
