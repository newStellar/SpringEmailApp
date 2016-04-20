package org.moto.Services;

import org.moto.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Nahid on 4/20/2016.
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(User user) throws MailException{

        SimpleMailMessage  mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmailAdress());
        mailMessage.setFrom("02nahid02@gmail.com");
        mailMessage.setSubject("spring-boot");
        mailMessage.setText(user.getMsg());

        mailSender.send(mailMessage);
    }

}
