package net.providence.postgresdataaccessms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.providence.postgresdataaccessms.model.Cliente;
import net.providence.postgresdataaccessms.model.Contacto;
import net.providence.postgresdataaccessms.model.Direccion;
import net.providence.postgresdataaccessms.model.Libro;
import net.providence.postgresdataaccessms.repository.ClienteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postgres-stress")
@RequiredArgsConstructor
public class ApiController {

    private final ClienteRepository clienteRepository;

    @PostMapping("/agregar/{datos}")
    public String generateData(@PathVariable Long datos) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        for(int i = 0 ; i < datos ; i++){
            Cliente cliente = new Cliente();
            cliente.setNombre(RandomStringUtils.randomAlphanumeric(40));
            cliente.setApellidos(RandomStringUtils.randomAlphanumeric(40));
            cliente.setCorreo(RandomStringUtils.randomAlphanumeric(30));
            cliente.setFechaNacimiento(new Date());

            Libro libro = new Libro();
            libro.setNombre(RandomStringUtils.randomAlphanumeric(20));
            libro.setAutor(RandomStringUtils.randomAlphanumeric(20)+"/"+RandomStringUtils.randomAlphanumeric(20));
            libro.setIsbn(RandomStringUtils.randomAlphanumeric(10));

            cliente.setLibro(libro);

//            Direccion direccion = new Direccion();
//            direccion.setNumero("22");
//            direccion.setCalle("Gobernadores");
//            direccion.setCodigoPostal("99999");
//            direccion.setColonia("Héroes de Chalco");
//
//            cliente.setDireccion(direccion);

            List<Contacto> contactos = new ArrayList<>();
            for(int j = 0 ; j < 10 ; j++){
                Contacto contacto = new Contacto();
                contacto.setNombre(RandomStringUtils.randomAlphanumeric(60));
                contactos.add(contacto);
            }
            cliente.setContactos(contactos);

            clienteRepository.save(cliente);
        }

        return "OK";
    }

    @GetMapping("/id/{id}")
    public Cliente traePorId(@PathVariable Long id){
        return clienteRepository.findById(id).orElseThrow();
    }

    @GetMapping("/list")
    public List<Cliente> traeData(){
        return (List<Cliente>) clienteRepository.findAll();
    }

    @DeleteMapping("/flush")
    public String flushData(){
        clienteRepository.deleteAll();
        return "OK";
    }

}
