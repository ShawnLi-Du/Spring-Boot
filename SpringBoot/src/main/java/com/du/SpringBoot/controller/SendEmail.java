package com.du.SpringBoot.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


public class SendEmail {
	// ----------------------------------------基本資料
	private String userName = "du1021du@gmail.com"; // 寄件者mail
	private String password = "hvalrgshyuemdror"; // 寄件者密碼<非真實密碼, 用安全信-應用程式密碼>
	private String customer = "du1021du@gmail.com"; // 收件者mail
	private String subject = "找回密碼"; // mail標題
	private String text = "mail內容"; // mail內容

	public void SendMail(String pw) {

		// ----------------------------------------連線設定
		Properties prop = new Properties();

		// 默認連線方式為smtp
		prop.setProperty("mail.transport.protocol", "smtp");
		// host : smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");
		// host port 465
		prop.put("mail.smtp.port", "465");
		// 寄件者帳號需要驗證：是
		prop.put("mail.smtp.auth", "true");
		// 需要安全資料傳輸層 (SSL)：是
		// <請mail.smtp.socketFactory.class去實作javax.net.ssl.SSLSocketFactory連線>
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// 安全資料傳輸層 (SSL) 通訊埠：465
		prop.put("mail.smtp.ssl.socketFactory.port", "465");
		//連線資訊
		prop.put("mail.debug", "true");

		// ----------------------------------------帳號驗證
		// ----------------------------------------Session javamail api 默認設定屬性

		// 1.透過匿名類別方式
		// Session session = Session.getDefaultInstance(prop, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userName, password);
//			}
//		});

		// 2.一般class方式
		Auth auth = new Auth(userName, password);
		Session session = Session.getDefaultInstance(prop, auth);
		// ----------------------------------------Message 放入基本資料
		MimeMessage message = new MimeMessage(session);

		try {
			// 寄件者
			// 1.匿名類別 方式
//			message.setSender(new InternetAddress(userName));
			// 2. class 放式
			InternetAddress sender = new InternetAddress(userName);
			message.setSender(sender);
			// 收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			// 標題
			message.setSubject(subject);
			// 內容/格式
			message.setContent(pw, "text/html;charset=UTF-8");
			
			// ----------------------------------------Transport 將 Message 傳出去

			Transport transport = session.getTransport();
			transport.send(message); //信件寄出
			transport.close();
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		
	}

}

class Auth extends Authenticator {



	private String userName;
	private String password;

	public Auth(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(userName, password);
		return pa;
	}

}
