package com.cn.myspring.controller;

import example.aspect.IUserService;
import junit.test.HelloJob;
import org.junit.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/HelloworldController")
public class HelloworldController {

    @Autowired
    IUserService userService;

    @Autowired
    @Qualifier(value = "scheduler")
    private SchedulerFactoryBean schedulerFactoryBean;

    private static String jobNameGrobal = "";


    /**
     * test demo
     * http://localhost:8080/heading-spring/HelloworldController/helloworld.do
     *
     * @return
     */
    @RequestMapping("/helloworld")
    public ModelAndView hello() {
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("test5");
        userService.addUser();
        ModelAndView model = new ModelAndView();
        model.addObject("fsfsdfdsf");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        addJob();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping("/helloworld2")
    public ModelAndView hello2() {
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        userService.addUser();
        ModelAndView model = new ModelAndView();
        model.addObject("fsfsdfdsf");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        updateJob();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    @RequestMapping("/helloworld3")
    public ModelAndView hello3() {
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        System.out.println("helloworld2****************************");
        userService.addUser();
        ModelAndView model = new ModelAndView();
        model.addObject("fsfsdfdsf");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        deleteJob();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping("/helloworld4")
    public ModelAndView hello4() {
        System.out.println("helloworld4****************************");
        System.out.println("helloworld4****************************");
        System.out.println("helloworld4****************************");
        System.out.println("helloworld4****************************");
        userService.addUser();
        ModelAndView model = new ModelAndView();
        model.addObject("fsfsdfdsf");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pauseJob();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping("/helloworld5")
    public ModelAndView hello5() {
        System.out.println("helloworld5****************************");
        System.out.println("helloworld5****************************");
        System.out.println("helloworld5****************************");
        System.out.println("helloworld5****************************");
        userService.addUser();
        ModelAndView model = new ModelAndView();
        model.addObject("fsfsdfdsf");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        resumeJob();
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    @Test
    public void addJob() throws SchedulerException {

        String JobName = "chensjob" + System.currentTimeMillis();
        jobNameGrobal = JobName;
        String JobGroup = "chensJobGroup";
        String cronExpression = "0/12 * * * * ?";
//        String cronExpression = "0 0/2 * * * ? ";

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // 启动调度器
        scheduler.start();

//        TriggerKey triggerKey = TriggerKey.triggerKey(JobName, JobGroup);
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(JobName, JobGroup).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(JobName, JobGroup).withSchedule(scheduleBuilder).build();

        Map<String, Object> argMap = new HashMap<>();
        argMap.put("ky", "chen");
        if (argMap != null) {
            trigger.getJobDataMap().putAll(argMap);
        }
//        scheduler.addJob(jobDetail,true);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void updateJob() throws SchedulerException {

        try {
            String jobName = this.jobNameGrobal;
            String jobGroupName = "chensJobGroup";
            String cronExpression = "0 0/3 * * * ? ";
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //修改map
//            if (argMap != null) {
//                trigger.getJobDataMap().putAll(argMap);
//            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteJob() throws SchedulerException {

        try {
            String jobName = this.jobNameGrobal;
            String jobGroupName = "chensJobGroup";
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void pauseJob() throws SchedulerException {

        try {
            String jobName = this.jobNameGrobal;
            String jobGroupName = "chensJobGroup";
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void resumeJob() throws SchedulerException {

        try {
            String jobName = this.jobNameGrobal;
            String jobGroupName = "chensJobGroup";
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.resumeTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/test2")
    public String test2() {
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("test5");
        return "hehe";
    }

    @RequestMapping("/test3")
    public void test3(@RequestParam String userName) {
        System.out.println("name:" + userName);
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("test5");
    }

    @RequestMapping("/test4")
    public void test4(@RequestBody String userName, String pwd) {

        System.out.println("userName:" + userName);
        System.out.println("pwd:" + pwd);
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("fsdfsfsfdfdsf****************************");
        System.out.println("test5");
    }

}
