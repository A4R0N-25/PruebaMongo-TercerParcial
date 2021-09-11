package ec.edu.espe.distribuidas.pruebaMongo.consultas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cajero")
public class Cajero {

    @Id
    private String id;

    private String nombre;

    private String direccion;

    private Integer monto;

    private Integer billetesVeinte;

    private Integer billetesDiez;
}
