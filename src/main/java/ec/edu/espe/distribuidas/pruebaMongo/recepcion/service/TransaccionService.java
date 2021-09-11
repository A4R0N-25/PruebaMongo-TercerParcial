package ec.edu.espe.distribuidas.pruebaMongo.recepcion.service;

import ec.edu.espe.distribuidas.pruebaMongo.recepcion.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.dto.TransaccionDto;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.model.Transaccion;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public void ingresarTransaccion(TransaccionDto transaccionDto){
        Transaccion transaccion = Transaccion.builder()
                .tipo(transaccionDto.getTipo())
                .cajero(transaccionDto.getCajero())
                .fecha(new Date())
                .estado("no")
                .monto(transaccionDto.getMonto())
                .billetesDiez(transaccionDto.getBilletesDiez())
                .billetesVeinte(transaccionDto.getBilletesVeinte())
                .build();
        transaccionRepository.save(transaccion);
    }
}
