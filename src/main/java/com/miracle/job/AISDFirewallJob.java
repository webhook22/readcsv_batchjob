package com.miracle.job;

import com.miracle.model.AISDFirewall;
import com.miracle.processor.AISDFirewallProcessor;
import com.miracle.reader.AISDFirewallReader;
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
public class AISDFirewallJob {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    AISDFirewallReader aisdFirewallReader;

    @Bean(name = "aisdFirewallJob")
    public Job aisdFirewallJob(Step aisdFirewallStepIndex) {
        return jobBuilderFactory.get("aisdFirewallJob")
                .incrementer(new RunIdIncrementer())
                .start(aisdFirewallStepIndex)
                .build();
    }

    @Bean
    @JobScope
    public Step aisdFirewallStepIndex() {
        return stepBuilderFactory.get("aisdFirewallStepIndex")
                .<AISDFirewall, AISDFirewall>chunk(1000)
                .reader(aisdFirewallMultiResourceItemReader())
                .processor(aisdFirewallProcessor())
                .writer(aisdFirewallWriter()).build();
    }

    @Bean
    public MultiResourceItemReader<AISDFirewall> aisdFirewallMultiResourceItemReader() {
        return aisdFirewallReader.aisdFirewallMultiResourceItemReader();
    }

    @Bean
    public AISDFirewallProcessor aisdFirewallProcessor() {
        return new AISDFirewallProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<AISDFirewall> aisdFirewallWriter() {
        JdbcBatchItemWriter<AISDFirewall> writer = new JdbcBatchItemWriter<AISDFirewall>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO AISDFirewall (domain,receive_Time,serial,type,threat_Content_Type,config_Version,generate_Time,virtual_system,event_id,stage,auth_method,tunnel_type,source_user,src_region,machine_name,public_ip,public_ipv6,private_ip,private_ipv6,host_id,serial_number,client_ver,client_os,client_os_ver,repeat_count,reason,error,Description,status,location,login_duration,connect_method,error_code,portal,sequence_number,action_flags) "
                + "VALUES (:domain, :receive_Time, :serial, :type, :threat_Content_Type, :config_Version, :generate_Time, :virtual_system, :event_id, :stage, :auth_method, :tunnel_type, :source_user, :src_region, :machine_name, :public_ip, :public_ipv6, :private_ip, :private_ipv6, :host_id, :serial_number, :client_ver, :client_os, :client_os_ver, :repeat_count, :reason, :error, :Description, :status, :location, :login_duration, :connect_method, :error_code, :portal, :sequence_number, :action_flags)");
        writer.setDataSource(dataSource);
        return writer;
    }
}
