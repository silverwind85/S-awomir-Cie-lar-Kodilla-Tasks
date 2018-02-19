package com.crud.tasks.service;


import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            LOGGER.info("Email has been sent");
            javaMailSender.send(createMimeMessage(mail));
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
            e.printStackTrace();
        }
    }
    private MimeMessagePreparator createMimeMessage(final  Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper =new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()),true);
        };
    }

    public SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc()!=null && !mail.getToCc().isEmpty()){
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }

}

