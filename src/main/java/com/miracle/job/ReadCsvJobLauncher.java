package com.miracle.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ReadCsvJobLauncher {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("aisdFirewallJob")
    private Job aisdFirewallJob;

    @Autowired
    @Qualifier("cbReportJob")
    private Job cbReportJob;

    @Scheduled(cron = "${cronExpression}")
    public void runAISDFirewallJob() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            jobLauncher.run(aisdFirewallJob, jobParameters);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @Scheduled(cron = "${cronExpression}")
    public void runCBReportJob() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            jobLauncher.run(cbReportJob, jobParameters);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
