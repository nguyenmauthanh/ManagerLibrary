package com.elcom.managerlibrary.scheduler;

import com.elcom.managerlibrary.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MailSender mailSender;

    private SimpleMailMessage simpleMailMessage;

    @Bean
    public void simpleMailMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        this.simpleMailMessage = message;
    }

    @Scheduled(cron = "0 17 11 * * *")
    public void sendMail(){

        SimpleMailMessage msg = new SimpleMailMessage(this.simpleMailMessage);
        msg.setTo("nguyenmauthanh21@gmail.com");
        msg.setSubject("THONG TIN VE TAC GIA CUA THU VIEN");
        msg.setText("SO LUONG TAC GIA TRONG THOI GIAN HIEN TAI " + authorService.countAuthor());

        try {
            this.mailSender.send(msg);
            LOGGER.info("Gui mail thanh cong");
        }catch (MailException ex){
            LOGGER.error("Gui mail khong thanh cong!!!!!");
        }
    }

}
