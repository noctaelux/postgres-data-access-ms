package net.providence.postgresdataaccessms.model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(name = "Direccion")
@ToString
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
