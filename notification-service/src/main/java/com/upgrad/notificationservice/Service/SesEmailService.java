package com.upgrad.notificationservice.Service;

import com.upgrad.notificationservice.Model.Appointment;
import com.upgrad.notificationservice.Model.Doctor;
import com.upgrad.notificationservice.Model.Prescription;
import com.upgrad.notificationservice.Model.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class SesEmailService {

    private String fromEmail = "sbaid40@hotmail.com";
    private String accessKey;
    private String secretKey;

    @Autowired
    FreeMarkerConfig configurer;
    // method to send an approval email to doctor's email id
    public void sendApprovalEmail(Doctor user) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("DoctorApproval.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(user.getEmailId(),"Welcome Email",htmlBody);
    }
    // method to send an rejection email to doctor's email id
    public void sendRejectionEmail(Doctor user) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("DoctorRejection.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(user.getEmailId(),"Rejection Email",htmlBody);
    }

    // method to send an appointment email to user's email id
    public void sendAppointmentEmail(Appointment appointment) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",appointment);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("AppointmentConfirmation.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(appointment.getUserEmailId(),"Appointment Confirmation",htmlBody);
    }
    // method to send an prescription email to user's email id
    public void sendPrescriptionEmail(Prescription prescription, User user, Doctor doctor) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("medicineList",prescription.getMedicineList());
        templateModel.put("user",user);
        templateModel.put("doctor", doctor);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("Prescription.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(user.getEmailId(),"Prescription",htmlBody);
    }
    //method to send email with smtp config
    private void sendSimpleMessage(String toEmail, String subject, String body) throws  MessagingException {
        accessKey="";
        secretKey="";
        Properties props = System.getProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(fromEmail);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect("email-smtp.us-east-1.amazonaws.com", accessKey, secretKey);
            transport.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            transport.close();
        }
    }
}
