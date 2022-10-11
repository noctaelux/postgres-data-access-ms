package net.providence.postgresdataaccessms.model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@XmlRootElement
public class Direccion {

    @XmlElement(name = "CodigoPostal")
    private String codigoPostal;
    @XmlElement(name = "Calle")
    private String calle;
    @XmlElement(name = "Colonia")
    private String colonia;
    @XmlElement(name = "Numero")
    private String numero;
}
