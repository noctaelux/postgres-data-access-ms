package net.providence.postgresdataaccessms.controller;

import lombok.RequiredArgsConstructor;
import net.providence.postgresdataaccessms.model.Cliente;
import net.providence.postgresdataaccessms.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postgres-stress")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/agregar/{datos}")
    public String generateData(@PathVariable Long datos) {
        String resp = "OK";
        try {
            apiService.addData(datos);
        } catch (JAXBException e) {
            resp = e.getMessage();
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping(value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevoCliente(@RequestBody Cliente cliente){
        apiService.nuevo(cliente);
        return "OK";
    }

    @PutMapping("/cliente")
    @ResponseStatus(HttpStatus.OK)
    public Cliente actualizaCliente(@RequestBody Cliente cliente){
        return apiService.actualizar(cliente);
    }

    @DeleteMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String removerCliente(@PathVariable Long id){
        apiService.removerCliente(id);
        return "OK";
    }

    @GetMapping("/cliente/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente traePorId(@PathVariable Long id){
        return apiService.getById(id);
    }

    @GetMapping("/cliente/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> traeData(){
        return apiService.getList();
    }

    @DeleteMapping("/clientes/flush")
    @ResponseStatus(HttpStatus.OK)
    public String flushData(){
        apiService.flushData();
        return "OK";
    }

}
