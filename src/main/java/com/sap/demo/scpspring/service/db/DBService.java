package com.sap.demo.scpspring.service.db;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.sap.demo.scpspring.exception.SAPException;

@Service
public class DBService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DBPrivateKeyGenerator dbPrivateKeyGenerator;

	public String fetchAccessTokenFromDB() throws ParseException, JOSEException {
		try {
			String JWSPrivateKey = dbPrivateKeyGenerator.generatePrivateKey();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String url = "https://simulator-api.db.com/gw/oidc/token";
			String requestJson = "grant_type=client_credentials&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer"
					+ "&client_assertion=" + JWSPrivateKey;
			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			String answer = restTemplate.postForObject(url, entity, String.class);
			// JSON from String to Object
			ObjectMapper mapper = new ObjectMapper();
			DBAccessObject accessObject = mapper.readValue(answer, DBAccessObject.class);
			return accessObject.getAccessToken();
		} catch (IOException e) {
			throw new SAPException("Could not get token from DB API");
		}
	}

	public String getCashAccounts() {
		try {
			String token = fetchAccessTokenFromDB();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

			String requestJson = "{}";
			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			String answer = restTemplate.postForObject(" https://simulator-api.db.com/gw/dbapi/v1/processingOrders",
					entity, String.class);
			// HttpEntity<?> entity = new HttpEntity<>(headers);
			// HttpEntity<String> response = restTemplate.exchange(
			// "https://simulator-api.db.com/gw/dbapi/v1/cashAccounts",
			// HttpMethod.GET,
			// entity,
			// String.class);
			// 2. Convert JSON to List of Person objects
			// Define Custom Type reference for List<Person> type
			// TypeReference<List<CashAccount>> mapType = new
			// TypeReference<List<CashAccount>>() {};
			// ObjectMapper objectMapper = new ObjectMapper();
			// //Set pretty printing of json
			// objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			//
			// List<CashAccount> jsonToPersonList =
			// objectMapper.readValue(response.getBody(), mapType);
			// return jsonToPersonList;
			return answer;
		} catch (ParseException e) {
			throw new SAPException("Could not get token from DB API" + e.getMessage());
		} catch (JOSEException e) {
			throw new SAPException("Could not get token from DB API" + e.getMessage());
		}

	}

}
