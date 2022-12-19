package net.providence.postgresdataaccessms.service;

import lombok.RequiredArgsConstructor;
import net.providence.postgresdataaccessms.exception.NoSuchElementFoundException;
import net.providence.postgresdataaccessms.model.Cliente;
import net.providence.postgresdataaccessms.model.Contacto;
import net.providence.postgresdataaccessms.model.Direccion;
import net.providence.postgresdataaccessms.model.Libro;
import net.providence.postgresdataaccessms.repository.ClienteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiServiceImpl implements ApiService{

    private final ClienteRepository clienteRepository;

    @Override
    public void addData(Long datos) throws JAXBException {

        for(int i = 0 ; i < datos ; i++){

            Libro libro = Libro.builder()
                    .nombre(RandomStringUtils.randomAlphanumeric(20))
                    .autor(RandomStringUtils.randomAlphanumeric(20)+"/"+RandomStringUtils.randomAlphanumeric(20))
                    .isbn(RandomStringUtils.randomAlphanumeric(10))
                    .build();

            Direccion direccion = Direccion.builder()
                    .id(RandomStringUtils.randomNumeric(6))
                    .calle(RandomStringUtils.randomAlphanumeric(15))
                    .colonia(RandomStringUtils.randomAlphanumeric(20))
                    .codigoPostal(RandomStringUtils.randomNumeric(6))
                    .numero(RandomStringUtils.randomNumeric(3))
                    .build();

            StringWriter swDireccion = new StringWriter();

            JAXBContext context = JAXBContext.newInstance(Direccion.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(direccion,swDireccion);

            List<Contacto> contactos = new ArrayList<>();
            for(int j = 0 ; j < 10 ; j++){
                contactos.add(new Contacto(RandomStringUtils.randomAlphanumeric(60)));
            }

            Cliente cliente = Cliente.builder()
                    .nombre(RandomStringUtils.randomAlphanumeric(40))
                    .apellidos(RandomStringUtils.randomAlphanumeric(40))
                    .correo(RandomStringUtils.randomAlphanumeric(30))
                    .fechaNacimiento(new Date())
                    .libro(libro)
                    .direccion(swDireccion.toString())
                    .contactos(contactos)
                    .build();

            clienteRepository.save(cliente);
        }
    }

    @Override
    public void nuevo(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Cliente cliente) {

        if(clienteRepository.findById(cliente.getId()).isPresent()){
            return clienteRepository.save(cliente);
        }else {
            throw new NoSuchElementFoundException("No se ha encontrado el cliente.");
        }
    }

    @Override
    public void removerCliente(Long id) {
        if (clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
        }else {
            throw new NoSuchElementFoundException("No se ha encontrado el cliente.");
        }

    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Cliente> getList() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public void flushData() {
        clienteRepository.deleteAll();
    }
}
