package com.miracle.job;

import com.miracle.model.CBReport;
import com.miracle.processor.CBReportProcessor;
import com.miracle.reader.CBReportReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;


@Configuration
@EnableScheduling
@Slf4j
@AllArgsConstructor
public class CBReportBatchJob {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    CBReportReader cbReportReader;

    @Bean(name = "cbReportJob")
    public Job cbReportJob(Step cbReportStepIndex) {
        return jobBuilderFactory.get("cbReportJob")
                .incrementer(new RunIdIncrementer())
                .start(cbReportStepIndex)
                .build();
    }

    @Bean
    @JobScope
    public Step cbReportStepIndex() {
        return stepBuilderFactory.get("cbReportStepIndex")
                .<CBReport, CBReport>chunk(1000)
                .reader(cbReportMultiResourceItemReader())
                .processor(cbReportProcessor())
                .writer(cbReportWriter()).build();
    }

    @Bean
    public MultiResourceItemReader<CBReport> cbReportMultiResourceItemReader() {
        return cbReportReader.cbReportMultiResourceItemReader();
    }

    @Bean
    public CBReportProcessor cbReportProcessor() {
        return new CBReportProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CBReport> cbReportWriter() {
        JdbcBatchItemWriter<CBReport> writer = new JdbcBatchItemWriter<CBReport>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO cbReports (DeviceID,SerialNumber,AnnotatedAssetID,Model,MacAddress,MostRecentUser,LastSync,MajorChromeVersion) "
                + "VALUES (:deviceID,:serialNumber,:annotatedAssetID,:model,:macAddress,:mostRecentUser,:lastSync,:majorChromeVersion)");
        writer.setDataSource(dataSource);
        return writer;
    }
}
