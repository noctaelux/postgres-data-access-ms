package net.providence.postgresdataaccessms.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Contacto {

    public Contacto(String nombre){
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Contacto() {

    }
}
