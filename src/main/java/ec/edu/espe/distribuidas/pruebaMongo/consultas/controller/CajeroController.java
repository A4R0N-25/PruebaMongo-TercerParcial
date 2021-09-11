package ec.edu.espe.distribuidas.pruebaMongo.consultas.controller;

import ec.edu.espe.distribuidas.pruebaMongo.consultas.model.Cajero;
import ec.edu.espe.distribuidas.pruebaMongo.consultas.service.CajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cajero/")
public class CajeroController {

    public final CajeroService cajeroService;

    public CajeroController(CajeroService cajeroService) {
        this.cajeroService = cajeroService;
    }

    @GetMapping(value = "{monto}")
    public ResponseEntity buscarPorMonto(@PathVariable Integer monto){
        try {
            List<Cajero> cajeros = cajeroService.menorAlMonto(monto);
            return ResponseEntity.ok(cajeros);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping(value = "id/{nombre}")
    public ResponseEntity buscarPorCajero(@PathVariable String nombre){
        try {
            Cajero cajero = cajeroService.bucarPorCajero(nombre);
            return ResponseEntity.ok(cajero);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
