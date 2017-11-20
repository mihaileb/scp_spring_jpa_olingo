package com.sap.demo.scpspring.rest.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.demo.scpspring.service.db.DBService;

@RestController
@RequestMapping(path = "/dbapi")
public class DBController {
	
	@Autowired
	private DBService dbService;

	@RequestMapping(path = "/demo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDemoCashAccounts() {
		return ResponseEntity.ok("Working");
	}

}

