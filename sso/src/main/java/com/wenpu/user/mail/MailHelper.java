package com.wenpu.user.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailHelper {

	private MailSender mailSender;

	private String mailFrom;


	public void setMailSender( MailSender mailSender ) {
		this.mailSender = mailSender;
	}


	public boolean sendMail( String to, String subject, String msg ) {
		return sendMail(this.mailFrom, to, subject, msg);
	}


	private boolean sendMail( String from, String to, String subject, String msg ) {
		//creating message
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		try {
			//sending message
			mailSender.send(message);
			return true;
		} catch ( MailException e ) {
			e.printStackTrace();
			return false;
		}
	}


	public String getMailFrom() {
		return mailFrom;
	}


	public void setMailFrom( String mailFrom ) {
		this.mailFrom = mailFrom;
	}

}
