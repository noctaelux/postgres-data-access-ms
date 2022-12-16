package net.providence.postgresdataaccessms.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Builder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String correo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContacto")
    private List<Contacto> contactos;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Libro libro;
    @Type(type = "net.providence.postgresdataaccessms.dbtype.SQLXMLType")
    private String direccion;

    public Cliente() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
