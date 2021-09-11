package ec.edu.espe.distribuidas.pruebaMongo.consultas.dao;

import ec.edu.espe.distribuidas.pruebaMongo.consultas.model.Cajero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CajeroRepository extends MongoRepository<Cajero,String> {

    List<Cajero> findByMontoLessThan(Integer monto);

    Optional<Cajero> findByNombre(String nombre);
}
