package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message,String template){
        List<String> functionality = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_url","http://localhost:8080/crud");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("team",adminConfig.getTeam());
        context.setVariable("app_name",adminConfig.getAppName());
        context.setVariable("app_mail",adminConfig.getCompanyEmail());
        context.setVariable("app_goal",adminConfig.getCompanyGoal());
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("admin_config",adminConfig);
        context.setVariable("application_functionality",functionality);
        context.setVariable("number_of_tasks",taskRepository.count());
        context.setVariable("tasks",tasks);
        return templateEngine.process(template,context);
    }

}
