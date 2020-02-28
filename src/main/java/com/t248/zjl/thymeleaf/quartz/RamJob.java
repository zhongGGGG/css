package com.t248.zjl.thymeleaf.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RamJob implements Job {
    @Override
    public void execute(
            JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("启动定时任务......每十秒执行一次，共执行三次");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.get("level") + "" + jobDataMap.get("job"));
    }
}
