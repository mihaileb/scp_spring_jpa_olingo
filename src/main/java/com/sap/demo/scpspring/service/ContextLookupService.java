package com.sap.demo.scpspring.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextLookupService {
	private static final Logger logger = LoggerFactory.getLogger(ContextLookupService.class);
	public static Context getContext() {
		try {
			return new InitialContext();
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}