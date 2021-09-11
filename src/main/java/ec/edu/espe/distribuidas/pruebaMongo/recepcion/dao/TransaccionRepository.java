package ec.edu.espe.distribuidas.pruebaMongo.recepcion.dao;

import ec.edu.espe.distribuidas.pruebaMongo.recepcion.model.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransaccionRepository extends MongoRepository<Transaccion,String> {

    List<Transaccion> findByCajeroAndEstado(String cajero, String estado);
}
