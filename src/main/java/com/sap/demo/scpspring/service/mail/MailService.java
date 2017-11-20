package com.sap.demo.scpspring.service.mail;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sap.demo.scpspring.model.MailStatus;
import com.sap.demo.scpspring.model.MailTemplateData;
import com.sap.demo.scpspring.model.dto.MailTemplateResponseDTO;
import com.sap.demo.scpspring.service.ContextLookupService;

@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	public MailTemplateResponseDTO sendEmail(MailTemplateData data) {
		MailTemplateResponseDTO response = new MailTemplateResponseDTO();
		response.setData(data);
		Transport transport = null;
		try {
			Context ctx = ContextLookupService.getContext();
			Session session;
			session = (Session) ctx.lookup("java:comp/env/MailResource");
			// Construct message from parameters
			transport = session.getTransport();
			transport.connect();

			logger.debug("Transport for 'smtp' connected");

			MimeMessage mimeMessage = new MimeMessage(session);
			InternetAddress[] fromAddress = InternetAddress.parse(data.getFrom());
			InternetAddress[] toAddresses = InternetAddress.parse(data.getTo());
			mimeMessage.setFrom(fromAddress[0]);
			mimeMessage.setRecipients(RecipientType.TO, toAddresses);
			mimeMessage.setSubject(data.getSubject(), "UTF-8");
			MimeMultipart multiPart = new MimeMultipart("alternative");
			MimeBodyPart part = new MimeBodyPart();
			part.setText(data.getBody(), "utf-8", "plain");
			multiPart.addBodyPart(part);
			mimeMessage.setContent(multiPart);

			logger.debug("Send MIME message {}", mimeMessage.getFrom()[0].toString());

			// Send mail
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

			logger.info("Mail send - {}", mimeMessage.getSubject());
			response.setStatus(MailStatus.SENT);
		} catch (Exception e) {
			logger.error("Mail operation failed.", e);
			response.setStatus(MailStatus.ERROR);
		} finally {
			// Close transport layer
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					logger.error("Mail operation failed.", e);
					response.setStatus(MailStatus.ERROR);
				}
			}
		}
		return response;
	}

}
