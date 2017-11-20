package com.sap.demo.scpspring.config;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.demo.scpspring.service.ContextLookupService;

@Configuration
public class MailConfiguration {

	@Bean
	public Session getMailSession() {
		Context ctx = ContextLookupService.getContext();
		Session session = null;
		try {
			session = (Session) ctx.lookup("java:comp/env/MailResource");
		} catch (NamingException e) {
			return null;
		}
		return session;
	}
	
}
