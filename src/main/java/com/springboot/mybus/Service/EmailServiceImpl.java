package com.springboot.mybus.Service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Properties;

@Service
@Transactional
public class EmailServiceImpl implements EmailService{
    @Override
    public boolean sendEmail(String message, String to) {

        boolean f=false;

        String from="mm0048511@gmail.com";

        String subject="Ticket details from BusBook";

        String host="smtp.gmail.com";

        Properties properties=System.getProperties();
        System.out.println("PROPERTIES"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mm0048511@gmail.com","qleelnjvjsvbkeae ");
            }
        });

        session.setDebug(true);

        MimeMessage m=new MimeMessage(session);

        try{
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            m.setSubject(subject);
            message=message.replace("\n","");

            m.setContent( message, "text/html; charset=utf-8" );
            //m.setText(message);

            Transport.send(m);

            System.out.println("Sent success...............");
            f=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}








