package junit.test;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.aspect.UserServiceImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


public class AspectTest {

    @Autowired
    @Qualifier(value = "schedulerFactoryBean")
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void test1() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        //UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        userService.addUser();
    }

    @Test
    public void quartzDemo2() throws SchedulerException {

        String JobName = "chensjob" + System.currentTimeMillis();
        String JobGroup = "chensJobGroup";
        String cronExpression = "0/10 * * * * ? *";

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // 启动调度器
        scheduler.start();

//        TriggerKey triggerKey = TriggerKey.triggerKey(JobName, JobGroup);
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(JobName, JobGroup).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(JobName, JobGroup).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
