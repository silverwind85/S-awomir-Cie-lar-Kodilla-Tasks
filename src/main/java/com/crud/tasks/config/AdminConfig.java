package com.crud.tasks.config;

import lombok.Getter;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${info.company.name}")
    private String AppName;

    @Value("${info.company.goal}")
    private String companyGoal;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${admin.team}")
    private String team;
}
