package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("test@test.com","Test","Test Messagee","");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        // When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);

    }
    @Test
    public void shouldSentWithoutCc(){
        //Give
        Mail mail = new Mail("test@test.com","Test","Test Messagee","");
        //When
        SimpleMailMessage mailMessage =simpleEmailService.createMailMessage(mail);
        //Then
        Assert.assertEquals(mailMessage.getCc(),null);
        Assert.assertEquals(mailMessage.getSubject(),"Test");
        Assert.assertEquals(mailMessage.getText(),"Test Messagee");
        Assert.assertEquals(mailMessage.getTo()[0],"test@test.com");

    }



}