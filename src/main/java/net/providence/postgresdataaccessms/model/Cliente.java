package net.providence.postgresdataaccessms.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@EqualsAndHashCode
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
    @Type(type = "net.providence.postgresdataaccessms.xmltype.SQLXMLType")
    private String direccion;

    public Cliente() {

    }
}
