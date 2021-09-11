package ec.edu.espe.distribuidas.pruebaMongo.recepcion.controller;

import ec.edu.espe.distribuidas.pruebaMongo.recepcion.dto.TransaccionDto;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.service.TransaccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/recepcion/")

public class TransaccionController {

    private final TransaccionService transaccionService;


    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public ResponseEntity insertarTransaccion(@RequestBody TransaccionDto transaccion){
        try {
            transaccionService.ingresarTransaccion(transaccion);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
