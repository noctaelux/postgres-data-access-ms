package net.providence.postgresdataaccessms.service;

import net.providence.postgresdataaccessms.model.Cliente;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ApiService {

    void addData(Long datos) throws JAXBException;

    void nuevo(Cliente cliente);

    Cliente actualizar(Cliente cliente);

    void removerCliente(Long id);

    Cliente getById(Long id);

    List<Cliente> getList();

    void flushData();

}
