package com.sap.demo.scpspring.service.db;

import java.io.File;
import java.io.IOException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class DBPrivateKeyGenerator {

	private String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();

	}
	public static void main(String[] args) throws ParseException, JOSEException {
		DBPrivateKeyGenerator generator= new DBPrivateKeyGenerator();
		System.out.println(generator.generatePrivateKey());
	}

	public String generatePrivateKey() throws ParseException, JOSEException {
		DBPrivateKeyGenerator obj = new DBPrivateKeyGenerator();
		String originalPublicKey = obj.getFile("publickeydb.json");
		JWKSet jwkSet = JWKSet.parse(originalPublicKey);
		StringBuilder builder = new StringBuilder();
		List<JWK> jwkList = jwkSet.getKeys();
		for (JWK jwk : jwkList) {
			String keyId = jwk.getKeyID();
			ECKey ecKey = (ECKey) jwkSet.getKeyByKeyId(keyId);
			ECPublicKey ecPublicKey = ecKey.toECPublicKey();
			ECPrivateKey ecPrivateKey = ecKey.toECPrivateKey();
			// Create the EC signer
			JWSSigner signer = new ECDSASigner(ecPrivateKey);
			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
					// For client_credentials the subject
					// and the issuer must be the client_id
					.subject("4ed0bf3a-f0aa-400a-861f-8faf9e74373b").issuer("4ed0bf3a-f0aa-400a-861f-8faf9e74373b")
					.audience("https://simulator-api.db.com/gw/oidc/token")
					.expirationTime(new Date(new Date().getTime() + 60 * 1000)).build();

			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.ES256), claimsSet);
			// Compute the EC signature
			signedJWT.sign(signer);
			// Serialize the JWS to compact form
			String serializedJWT = signedJWT.serialize();

			// On the consumer side, parse the JWS and verify its EC signature
			signedJWT = SignedJWT.parse(serializedJWT);

			JWSVerifier verifier = new ECDSAVerifier(ecPublicKey);
			String yourSerializedClientAssertion = signedJWT.serialize();
			builder.append(yourSerializedClientAssertion);
		}
		return builder.toString();
	}

}
