package ec.edu.espe.distribuidas.pruebaMongo.consolidacion.job;

import ec.edu.espe.distribuidas.pruebaMongo.consolidacion.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.pruebaMongo.consolidacion.tasks.Consolidar;
import ec.edu.espe.distribuidas.pruebaMongo.consultas.dao.CajeroRepository;
import ec.edu.espe.distribuidas.pruebaMongo.recepcion.dao.TransaccionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CajeroRepository cajeroRepository;

    @Autowired
    private ConsolidadoRepository consolidadoRepository;

    @Bean
    protected Step consolidar(){
        return steps
                .get("consolidar")
                .tasklet(new Consolidar(this.transaccionRepository, cajeroRepository,consolidadoRepository))
                .build();
    }

    @Bean
    public Job ConsolidarJob() {
        return jobs
                .get("ConsolidarJob")
                .start(consolidar())
                .build();
    }

    @Scheduled(fixedRate = 900000)
    public void launchJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(ConsolidarJob(), params);
    }
}
