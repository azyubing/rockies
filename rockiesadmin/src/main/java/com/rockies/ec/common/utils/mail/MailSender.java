package com.rockies.ec.common.utils.mail;

import java.security.GeneralSecurityException;
import java.security.Security;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import com.rockies.ec.common.utils.CommonUtils;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 用户新用户注册时候发送的email
 * 
 */
public class MailSender {
	
	/**
	 * 创建送信 session
	 * 
	 * @return Session
	 * @throws GeneralSecurityException 
	 */
	private static Session create_session() throws GeneralSecurityException {
		MailSSLSocketFactory sf = new MailSSLSocketFactory();  
	    sf.setTrustAllHosts(true);  
	    // or  
	    // sf.setTrustedHosts(new String[] { "my-server" });  
	     
	    // also use following for additional safety  
	    //props.put("mail.smtp.ssl.checkserveridentity", "true");  
	    
	    
		Properties props = new Properties();
		props.put("mail.smtp.host" , CommonUtils.getInterfaceConfigBykey("smtp.host"));
		props.put("mail.smtp.port" , CommonUtils.getInterfaceConfigBykey("smtp.port"));
		props.put("mail.smtp.auth" , CommonUtils.getInterfaceConfigBykey("smtp.auth"));
		props.put("mail.smtp.socketFactory.port" , CommonUtils.getInterfaceConfigBykey("smtp.port"));
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.socketFactory", sf);  
		props.put("mail.smtp.socketFactory.fallback" , "false");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CommonUtils.getInterfaceConfigBykey("auth.user"),CommonUtils.getInterfaceConfigBykey("auth.pwd"));
                }
        });
		return session;
	}
	
	/**
	 * 根据传入的文件路径创建附件并返回
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static MimeBodyPart createAttachment(String fileName) throws Exception {
		MimeBodyPart attachmentPart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(fileName);
		attachmentPart.setDataHandler(new DataHandler(fds));
		attachmentPart.setFileName(fds.getName());
		return attachmentPart;
	}

	/**
	 * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分
	 * 
	 * @param body
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static MimeBodyPart createContent(String body , String fileName) throws Exception {
		
		// 用于保存最终正文部分
		MimeBodyPart contentBody = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");

		// 正文的文本部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(body , "text/html;charset=gbk");
		contentMulti.addBodyPart(textBody);

		if(StringUtils.isNotEmpty(fileName)) {
			
			// 正文的图片部分
			MimeBodyPart jpgBody = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileName);
			jpgBody.setDataHandler(new DataHandler(fds));
			jpgBody.setContentID("img1");
			contentMulti.addBodyPart(jpgBody);
		}
		
		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		contentBody.setContent(contentMulti);
		return contentBody;
	}

	
	/**
	 * 发送email, 修改邮箱验证码
	 * 
	 * @param msg
	 * @param toAddress
	 * @throws Exception
	 */
	public static void send_confirm_ok_mail(String str,String toAddress) throws Exception {
		Session session = create_session();
		// 创建MIME邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(CommonUtils.getInterfaceConfigBykey("auth.user")));// 发件人
		mimeMessage.setRecipient(Message.RecipientType.TO , new InternetAddress(toAddress));// 收件人
		mimeMessage.setSender(new InternetAddress(CommonUtils.getInterfaceConfigBykey("auth.user")));
		mimeMessage.setHeader("Content-Type" , "text/html;charset=gbk");
		mimeMessage.setHeader("Content-Transfer-Encoding" , "7bit");
		SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		mimeMessage.setHeader("Date" , format.format(new Date()));
		mimeMessage.setSentDate(new Date());// 发送日期
		
		mimeMessage.setSubject(MimeUtility.encodeText("【麦味旅行】您的行程单已经全部确认"));
		
		String sendMsg = CommonUtils.getMsgTemplateBykey("confirm_ok_mail");
		sendMsg = MessageFormat.format(sendMsg, str);
    
		MimeBodyPart content = createContent(sendMsg, null);
		
		MimeMultipart allPart = new MimeMultipart("mixed");  
		allPart.addBodyPart(content);
		mimeMessage.setContent(allPart);
		
		Transport.send(mimeMessage);// 发送邮件
	}
}
