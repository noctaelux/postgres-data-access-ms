package net.providence.postgresdataaccessms.controller;

import lombok.RequiredArgsConstructor;
import net.providence.postgresdataaccessms.model.Cliente;
import net.providence.postgresdataaccessms.repository.ClienteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postgres-stress")
@RequiredArgsConstructor
public class ApiController {

    private final ClienteRepository clienteRepository;

    @PostMapping("/agregar/{datos}")
    public String generateData(@PathVariable Long datos){
        for(int i = 0 ; i < datos ; i++){
            Cliente cliente = new Cliente();
            cliente.setNombre(RandomStringUtils.randomAlphanumeric(40));
            cliente.setApellidos(RandomStringUtils.randomAlphanumeric(40));
            cliente.setCorreo(RandomStringUtils.randomAlphanumeric(30));
            cliente.setFechaNacimiento(new Date());

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
