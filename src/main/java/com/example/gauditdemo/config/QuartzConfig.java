package com.example.gauditdemo.config;

import com.example.gauditdemo.task.MysqlAddEsScheduler;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Frederic.Hu
 * @Description  Quartz的相关配置，注册JobDetail和Trigger
 * 注意JobDetail和Trigger是org.quartz包下的，不是spring包下的，不要导入错误
 * @date 2021/12/08 17:32
 */
@Configuration
public class QuartzConfig {

    /** builder 类创建了一个JobDetail和一个Trigger并注册成为Spring bean，
     * 这些bean会自动关联到调度器上，JobDetail和Trigger需要设置组名和自己的名字，用来作为唯一标识
     * JobDetail里有一个StartOfDayJob类，这个类就是job接口的一个实现类，里面定义了任务的具体内容
     */
    @Bean
    public JobDetail jobDetail() {
        // 指定具体的定时任务类
        JobDetail jobDetail = JobBuilder.newJob(MysqlAddEsScheduler.class)
                .withIdentity("myJob1", "myJobGroup1")
                .storeDurably()
                .build();
        return jobDetail;
    }

    /** Trigger通过corn表达式指定了任务执行的周期。 */
    @Bean
    public Trigger trigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("myTrigger1", "myTriggerGroup1")
                .startNow()
                // 0 */1 * * * ?   每分钟执行
                // */5 * * * * ?   每5s执行
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ?"))
                .build();
        // 返回任务触发器
        return trigger;
    }

    /** 这边你可以写另外一个定时任务 */
//    @Bean
//    public JobDetail jobDetail2() {
//        // 指定具体的定时任务类
//        JobDetail jobDetail = JobBuilder.newJob(ElasticSearchUtil.class)
//                .withIdentity("myJob2", "myJobGroup2")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    @Bean
//    public Trigger trigger2() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(jobDetail2())
//                .withIdentity("myTrigger2", "myTriggerGroup2")
//                .startNow()
//                // 每天0点执行  0 0 0 * * ?   这里设定执行方式
//                // 0 */1 * * * ?   每分钟执行
//                // */5 * * * * ?   每5s执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
//        // 返回任务触发器
//        return trigger;
//    }



}
