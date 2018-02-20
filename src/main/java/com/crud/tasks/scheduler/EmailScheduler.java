package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private final static String SUBJECT = "Task: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    /*@Scheduled(cron = "0 0 10 * * *")*/
    public Mail sendInformationEmail() {
        String plural;
        long size = taskRepository.count();
        plural = (taskRepository.count() > 1) ? "tasks" : "task";
        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got: " + size + " " +plural, "slcieslar@gmail.com");
        /*simpleEmailService.send(mail);*/

        return mail;
    }
    @Scheduled(cron ="0 0 10 * * *")
    /*@Scheduled(fixedDelay = 10000)*/
    public Mail sendInformationEmailThymeleaf(){
        Mail mail =new Mail(adminConfig.getAdminMail(),SUBJECT,"Currently in database you got: ","slcieslar@gmail.com");
        simpleEmailService.send(mail,"mail/trello-daily-task-information");
        return mail;
    }

}


