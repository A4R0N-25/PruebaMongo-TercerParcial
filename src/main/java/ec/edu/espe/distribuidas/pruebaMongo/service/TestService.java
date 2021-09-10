/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.pruebaMongo.service;

import ec.edu.espe.distribuidas.pruebaMongo.dao.TestRepository;
import ec.edu.espe.distribuidas.pruebaMongo.dto.TestDto;
import ec.edu.espe.distribuidas.pruebaMongo.model.Test;
import org.springframework.stereotype.Service;

/**
 *
 * @author bran-
 */
@Service
public class TestService {
    
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
    
    
    
    public void test(TestDto testDto){
        
        Test t = new Test();
        t.setTest(testDto.getTest());
        testRepository.save(t);
        
    }
    
}
