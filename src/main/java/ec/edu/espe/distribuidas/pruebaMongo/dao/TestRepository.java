/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.pruebaMongo.dao;

import ec.edu.espe.distribuidas.pruebaMongo.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author bran-
 */
public interface TestRepository extends MongoRepository<Test, String>{
    
}
