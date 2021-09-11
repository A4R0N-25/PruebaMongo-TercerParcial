package ec.edu.espe.distribuidas.pruebaMongo.recepcion.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransaccionDto {

    private String tipo;

    private String cajero;

    private Integer monto;

    private Integer billetesVeinte;

    private Integer billetesDiez;
}
