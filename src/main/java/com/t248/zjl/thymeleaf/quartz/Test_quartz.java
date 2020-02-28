package com.t248.zjl.thymeleaf.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;

public class Test_quartz {
//    public static void main(String[] args) throws SchedulerException {
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        JobDetail jobDetail = newJob(RamJob.class)
//                .withDescription("this is a job")
//                .withIdentity("job1","group1")
//                .usingJobData("level","傻逼")
//                .build();
//        JobDataMap jobDataMap = jobDetail.getJobDataMap();
//        jobDataMap.put("job","贱贱");
//        Trigger trigger = TriggerBuilder.newTrigger().withDescription("this is a trigger1")
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(3,10)).withIdentity("trigger1","group1").build();
//        //                .withSchedule(CronScheduleBuilder.cronSchedule("0 42 17 * * ?"))
//        //将触发器以及调度任务详情绑定到调度器上
//        scheduler.scheduleJob(jobDetail,trigger);
//        // 启动调度器
//        scheduler.start();
//    }
}
