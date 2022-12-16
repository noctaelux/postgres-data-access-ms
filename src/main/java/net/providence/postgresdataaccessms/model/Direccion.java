package net.providence.postgresdataaccessms.model;

import lombok.Builder;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Builder
@XmlRootElement(name = "direccion")
public class Direccion {

    @XmlAttribute
    private String id;
    @XmlElement
    private String codigoPostal;
    @XmlElement
    private String calle;
    @XmlElement
    private String colonia;
    @XmlElement
    private String numero;
}
