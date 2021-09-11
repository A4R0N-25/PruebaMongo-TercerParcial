package ec.edu.espe.distribuidas.pruebaMongo.consolidacion.tasks;

import ec.edu.espe.distribuidas.pruebaMongo.consolidacion.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.pruebaMongo.consolidacion.model.Consolidado;
import ec.edu.espe.distribuidas.pruebaMongo.consultas.dao.CajeroRepository;
import ec.edu.espe.distribuidas.pruebaMongo.consultas.model.Cajero;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.model.Transaccion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

@Slf4j
public class Consolidar implements Tasklet, StepExecutionListener {

    private final TransaccionRepository transaccionRepository;
    private final CajeroRepository cajeroRepository;
    private final ConsolidadoRepository consolidadoRepository;

    public Consolidar(TransaccionRepository transaccionRepository, CajeroRepository cajeroRepository,ConsolidadoRepository consolidadoRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cajeroRepository = cajeroRepository;
        this.consolidadoRepository = consolidadoRepository;
    }


    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Preparando para Consolidar");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("Iniciando el proceso de consolidado");
        List<Cajero> cajeros= cajeroRepository.findAll();
        for(Cajero cajero:cajeros){
            List<Transaccion> transaccions = transaccionRepository.findByCajeroAndEstado(cajero.getNombre(),"no");
            if (transaccions.isEmpty()){
                continue;
            }
            Integer monto = cajero.getMonto();
            Integer veinte = cajero.getBilletesVeinte();
            Integer diez = cajero.getBilletesDiez();
            for (Transaccion transaccion : transaccions){
                if("RET".equals(transaccion.getTipo())){
                    monto=monto-transaccion.getMonto();
                    veinte=veinte-transaccion.getBilletesVeinte();
                    diez=diez-transaccion.getBilletesDiez();
                }else if("DEP".equals(transaccion.getTipo())){
                    monto=monto+transaccion.getMonto();
                    veinte=veinte+transaccion.getBilletesVeinte();
                    diez=diez+transaccion.getBilletesDiez();
                }
                transaccion.setEstado("si");
                transaccionRepository.save(transaccion);
            }
            Consolidado consolidado = Consolidado.builder()
                    .cajero(cajero.getNombre())
                    .monto(monto)
                    .billetesDiez(diez)
                    .billetesVeinte(veinte)
                    .build();
            consolidadoRepository.save(consolidado);
            cajero.setMonto(monto);
            cajero.setBilletesDiez(diez);
            cajero.setBilletesVeinte(veinte);
            cajeroRepository.save(cajero);
        }
        log.info("Terminado el proceso de consolidado");
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Fin del proceso de consolidado");
        return ExitStatus.COMPLETED;
    }
}
