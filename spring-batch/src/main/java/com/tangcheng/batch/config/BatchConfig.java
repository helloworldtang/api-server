package com.tangcheng.batch.config;

import com.tangcheng.batch.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangcheng on 7/11/2017.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final int MAX_ITEMS = 4000;

    private static final int CHUNK_SIZE = 1000;

    Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    StepBuilderFactory stepBuilder;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    JobBuilderFactory jobFactory;

    @Autowired
    MyListener listener;


    @Autowired
    JobRepository jobRepository;
    /**
     * The very simple way of doing things. Do this, then this, ... end of story.
     * @return
     */
    @Bean
    public Job jobDefinedBySimpleJob(){
        SimpleJob simplejob = new SimpleJob("jobDefinedAsSimpleJob");
        simplejob.setJobRepository(jobRepository);
        simplejob.setJobParametersIncrementer( new RunIdIncrementer() );
        simplejob.addStep( loadFromList() );
        simplejob.addStep( printFromDB() );
        return simplejob;
    }

    @Bean
    public Job jobDefinedAsFlow(){
        return jobFactory.get("jobDefinedAsFlow").start( buildFlow() ).build().build();
    }

    /**
     * The idea of a "flow" is for the job to make decisions on what to do given exit statuses from the steps.
     * @return
     */
    @Bean
    public Flow buildFlow(){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("aFlow");
        flowBuilder.start( loadFromList() );
        flowBuilder.next( printFromDB() );

        return flowBuilder.build();
    }



    @Bean
    public Step loadFromList() {
        return stepBuilder.get("loadFromList")
                .<String, City> chunk(CHUNK_SIZE)
                .reader(getReader())
                .processor(getProcessor())
                .writer(getWriter())
                .build();
    }

    @Bean
    public Step printFromDB() {
        return stepBuilder.get("printFromDB")
                .<City,City>chunk(CHUNK_SIZE)
                .reader(  getReadFromJpa() )
                .writer( getWriteJpaValue() )
                .listener(  listener )
                .build();
    }

    @Component
    public static class MyListener{

        Logger log = LoggerFactory.getLogger(this.getClass());
        long itemsWritten=0;



        public MyListener() {
            super();
        }

        @AfterStep
        public ExitStatus afterStep(StepExecution stepExecution){
            log.info("I got " + getItemsWritten() + " items");
            return stepExecution.getExitStatus();
        }

        @AfterWrite
        public void afterWrite(List<String> items){
            itemsWritten += items.size();
            //LOGGER.debug("After step. Treated " + getItemsWritten() + " items." );
        }

        public long getItemsWritten() {
            return itemsWritten;
        }

    }

    @Bean
    public ItemReader<String> getReader() {
        List<String> list = new ArrayList<>();
        for (int i=0; i<MAX_ITEMS;i++){
            list.add( "V=" + i);
        }
        return new ListItemReader<String>(list);
    }

    @Bean
    public ItemProcessor<? super String, ? extends City> getProcessor() {
        return new ItemProcessor<String, City>() {
            @Override
            public City process(String item) throws Exception {
                City city = new City();
                city.setValue(item);
                return city;
            }
        };
    }

    @Bean
    public ItemWriter<? super City> getWriter() {
        JpaItemWriter<City> jpaItemWriter = new JpaItemWriter<City>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

    @Bean
    public ItemReader<? extends City> getReadFromJpa() {
        JpaPagingItemReader<City> reader = new JpaPagingItemReader<City>();
        reader.setPageSize(CHUNK_SIZE);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("from City");
        return reader;
    }

    @Bean
    public ItemWriter<? super City> getWriteJpaValue() {
        return new ItemWriter<City>(){
            @Override
            public void write(List<? extends City> items) throws Exception {
            }};
    }



}
