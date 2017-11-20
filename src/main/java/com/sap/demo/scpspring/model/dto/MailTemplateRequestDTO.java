package com.sap.demo.scpspring.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailTemplateRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String to;
	private List<String> invoiceNos= new ArrayList<String>();

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<String> getInvoiceNos() {
		return invoiceNos;
	}

	public void setInvoiceNos(List<String> invoiceNos) {
		this.invoiceNos = invoiceNos;
	}

}
