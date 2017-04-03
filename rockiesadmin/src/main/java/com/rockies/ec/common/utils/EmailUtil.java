package com.rockies.ec.common.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailUtil {
    private final static String SMTP_HOST = "smtp.exmail.qq.com";

    private final static String SMTP_PORT = "465";

    private final static String SENDER_EMAIL = CommonUtils.getPropertiesBySendEmail("senderEmail");
    private final static String SENDER_PASSWD = CommonUtils.getPropertiesBySendEmail("senderPwd");
    private final static String SENDER_NAME = CommonUtils.getPropertiesBySendEmail("senderName");
    
    public static boolean sendEmail(
        String recevierEmail, String title, String content) {
        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SMTP_HOST);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", SMTP_PORT);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", SMTP_PORT);
        props.setProperty("mail.smtp.auth", "true");

        Session mailSession = Session.getInstance(props, new Authenticator() {
            // 用户验证
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWD);
            }
        });

        // 设置调试，打印smtp信息
        mailSession.setDebug(true);
        try {
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            Multipart multipart = new MimeMultipart("alternative");

            // html形式的邮件正文
            BodyPart part = new MimeBodyPart();
            part.setHeader("Content-Type", "text/html;charset=UTF-8");
            part.setHeader("Content-Transfer-Encoding", "quoted-printable");
            part.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(part);

            message.setContent(multipart);
            message.setFrom(new InternetAddress(SENDER_EMAIL, SENDER_NAME, "UTF-8"));
            message.setSubject(title, "UTF-8");
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recevierEmail));

            transport.connect();
            transport.sendMessage(message, message.getRecipients(javax.mail.Message.RecipientType.TO));
            transport.close();
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            return false;
        }
    }

    public static void main(String args[]) {

        String receiverEmail = "jianghuiqing@51caixiang.com";

        sendEmail( receiverEmail, "您的审核已经通过", "<p>您的审核已经通过</p><p>商户前端网站地址:http://feixun.ec.51caixiangtest.com/</p><p>管理后台网站地址:http://admin.ec.51caixiangtest.com/site</p><p>商户管理员账号:</p><p>商户管理员密码(默认密码,请尽快修改)</p>");
    }
}
