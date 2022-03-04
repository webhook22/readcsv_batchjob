package com.miracle.reader;

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
public class CBReportReader {
    @Value("/cbReports.csv")
    private Resource[] cbReportResources;

    public MultiResourceItemReader<CBReport> cbReportMultiResourceItemReader() {
        MultiResourceItemReader<CBReport> resourceItemReader = new MultiResourceItemReader<CBReport>();
        resourceItemReader.setResources(cbReportResources);
        resourceItemReader.setDelegate(cbReportReader());
        return resourceItemReader;
    }

    public FlatFileItemReader<CBReport> cbReportReader() {
        FlatFileItemReader<CBReport> reader = new FlatFileItemReader<CBReport>();
        reader.setLinesToSkip(1);
//		reader.setResource(new ClassPathResource("cbReports.csv"));
        reader.setLineMapper(new DefaultLineMapper<CBReport>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("Device ID", "Serial Number", "Annotated Asset ID", "Model", "Mac Address", "Most Recent User", "Last Sync", "Major Chrome Version");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<CBReport>() {
                    {
                        setTargetType(CBReport.class);
                    }
                });
            }
        });
        return reader;
    }
}
