package net.providence.postgresdataaccessms.controller;

import lombok.RequiredArgsConstructor;
import net.providence.postgresdataaccessms.model.Cliente;
import net.providence.postgresdataaccessms.service.ApiService;
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
        }
        return resp;
    }

    @GetMapping("/id/{id}")
    public Cliente traePorId(@PathVariable Long id){
        return apiService.getById(id);
    }

    @GetMapping("/list")
    public List<Cliente> traeData(){
        return apiService.getList();
    }

    @DeleteMapping("/flush")
    public String flushData(){
        apiService.flushData();
        return "OK";
    }

}
