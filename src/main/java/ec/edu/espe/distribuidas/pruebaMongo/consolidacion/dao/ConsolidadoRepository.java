package ec.edu.espe.distribuidas.pruebaMongo.consolidacion.dao;

import ec.edu.espe.distribuidas.pruebaMongo.consolidacion.model.Consolidado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsolidadoRepository extends MongoRepository<Consolidado,String> {
}
