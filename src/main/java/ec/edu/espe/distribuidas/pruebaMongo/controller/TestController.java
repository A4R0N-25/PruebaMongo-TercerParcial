/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.pruebaMongo.controller;

import ec.edu.espe.distribuidas.pruebaMongo.dto.TestDto;
import ec.edu.espe.distribuidas.pruebaMongo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/v1/test")
public class TestController {
    
    @Autowired
    private TestService testService;
    
    @PostMapping
    public ResponseEntity test(@RequestBody TestDto testDto){
        try {
            testService.test(testDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
