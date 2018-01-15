package com.crud.tasks.scheduler;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {

    private final static String MESSAGE = "Currently in database you got: 1 task";

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    public void testSendInformationEmail(){
        //Given
        when(taskRepository.count()).thenReturn((long)1);
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
        //When
        Mail result = emailScheduler.sendInformationEmail();
        //Then
        Assert.assertEquals(result.getMessage(),MESSAGE);
    }
}
