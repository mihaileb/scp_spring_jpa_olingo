package com.sap.demo.scpspring.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.demo.scpspring.model.MailStatus;
import com.sap.demo.scpspring.model.MailTemplateData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailTemplateResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MailTemplateData data;
	private MailStatus status;

	public MailStatus getStatus() {
		return status;
	}

	public void setStatus(MailStatus status) {
		this.status = status;
	}

	public MailTemplateData getData() {
		return data;
	}

	public void setData(MailTemplateData data) {
		this.data = data;
	}

}
