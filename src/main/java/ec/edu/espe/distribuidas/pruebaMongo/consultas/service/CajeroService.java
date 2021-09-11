package ec.edu.espe.distribuidas.pruebaMongo.consultas.service;

import ec.edu.espe.distribuidas.pruebaMongo.consultas.dao.CajeroRepository;
import ec.edu.espe.distribuidas.pruebaMongo.consultas.model.Cajero;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CajeroService {

    private final CajeroRepository cajeroRepository;

    public CajeroService(CajeroRepository cajeroRepository) {
        this.cajeroRepository = cajeroRepository;
    }

    public List<Cajero> menorAlMonto(Integer monto){
        List<Cajero> cajeros = cajeroRepository.findByMontoLessThan(monto);
        return cajeros;
    }

    public Cajero bucarPorCajero(String nombreCajero) throws Exception {
        Optional<Cajero> cajero = cajeroRepository.findByNombre(nombreCajero);

        if(cajero.isEmpty()){
            throw new Exception("No se encontro");
        }

        return cajero.get();
    }
}
