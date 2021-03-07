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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/HelloworldController")
public class HelloworldController {

    @Autowired
    IUserService userService;

    @Autowired
    @Qualifier(value = "scheduler")
    private SchedulerFactoryBean schedulerFactoryBean;


    /**
     * test绫�
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
                        quartzDemo2();
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
    public void quartzDemo2() throws SchedulerException {

        String JobName = "chensjob" + System.currentTimeMillis();
        String JobGroup = "chensJobGroup";
        String cronExpression = "0/10 * * * * ?";

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // 启动调度器
        scheduler.start();

//        TriggerKey triggerKey = TriggerKey.triggerKey(JobName, JobGroup);
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(JobName, JobGroup).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(JobName, JobGroup).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);

        while (true) {

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
