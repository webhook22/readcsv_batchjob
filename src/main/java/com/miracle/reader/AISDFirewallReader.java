package com.miracle.reader;

import com.miracle.model.AISDFirewall;
import com.miracle.model.CBReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Configuration
public class AISDFirewallReader {
    @Value("/aisdFirewall.csv")
    private Resource[] aisdFirewallResources;

    public MultiResourceItemReader<AISDFirewall> aisdFirewallMultiResourceItemReader() {
        MultiResourceItemReader<AISDFirewall> resourceItemReader = new MultiResourceItemReader<AISDFirewall>();
        resourceItemReader.setResources(aisdFirewallResources);
        resourceItemReader.setDelegate(aisdFirewallReader());
        return resourceItemReader;
    }

    public FlatFileItemReader<AISDFirewall> aisdFirewallReader() {
        FlatFileItemReader<AISDFirewall> reader = new FlatFileItemReader<AISDFirewall>();
        reader.setLinesToSkip(1);
//		reader.setResource(new ClassPathResource("cbReports.csv"));
        reader.setLineMapper(new DefaultLineMapper<AISDFirewall>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("Domain","Receive Time","Serial","Type","Threat/Content Type","Config Version","Generate Time","Virtual System","Event ID","stage","auth_method","tunnel_type","Source User","srcregion","machinename"," Public IP","public_ipv6","private_ip","private_ipv6","hostid","serialnumber","client_ver","client_os","client_os_ver","Repeat Count","reason","error","Description","status","location","login_duration","connect_method","error_code","portal","Sequence Number","Action Flags");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<AISDFirewall>() {
                    {
                        setTargetType(AISDFirewall.class);
                    }
                });
            }
        });
        return reader;
    }

}
