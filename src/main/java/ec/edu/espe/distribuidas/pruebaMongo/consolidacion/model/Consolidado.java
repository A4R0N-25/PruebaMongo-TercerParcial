package ec.edu.espe.distribuidas.pruebaMongo.consolidacion.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "consolidado")
@Builder
public class Consolidado {

    @Id
    private String id;

    private String cajero;

    private Integer monto;

    private Integer billetesVeinte;

    private Integer billetesDiez;
}
