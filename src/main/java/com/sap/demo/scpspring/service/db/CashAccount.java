package com.sap.demo.scpspring.service.db;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashAccount implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("iban")
	private String iban;
	@JsonProperty("currencyCode")
	private String currencyCode;
	@JsonProperty("bic ")
	private String bic;
	@JsonProperty("accountType")
	private String accountType;
	@JsonProperty("currentBalance")
	private Integer currentBalance;
	@JsonProperty("productDescription")
	private String productDescription;

	@JsonProperty("iban")
	public String getIban() {
		return iban;
	}

	@JsonProperty("iban")
	public void setIban(String iban) {
		this.iban = iban;
	}

	@JsonProperty("currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	@JsonProperty("currencyCode")
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@JsonProperty("bic ")
	public String getBic() {
		return bic;
	}

	@JsonProperty("bic ")
	public void setBic(String bic) {
		this.bic = bic;
	}

	@JsonProperty("accountType")
	public String getAccountType() {
		return accountType;
	}

	@JsonProperty("accountType")
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@JsonProperty("currentBalance")
	public Integer getCurrentBalance() {
		return currentBalance;
	}

	@JsonProperty("currentBalance")
	public void setCurrentBalance(Integer currentBalance) {
		this.currentBalance = currentBalance;
	}

	@JsonProperty("productDescription")
	public String getProductDescription() {
		return productDescription;
	}

	@JsonProperty("productDescription")
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
