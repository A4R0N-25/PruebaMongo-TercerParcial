package ec.edu.espe.distribuidas.pruebaMongo.recepcion.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "transaccion")
@Builder
public class Transaccion {

    @Id
    private String id;

    private String tipo;

    private String cajero;

    private Date fecha;

    private String estado;

    private Integer monto;

    private Integer billetesVeinte;

    private Integer billetesDiez;

}
