package com.sap.demo.scpspring.rest.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.demo.scpspring.service.mail.MailService;

@RestController
@RequestMapping(path = "/mail")
public class MailController {

	@Autowired
	private MailService mailService;


//	@ApiOperation(value = "Sends an email with the corresponding payload", response = MailTemplateResponseDTO.class)
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public ResponseEntity<MailTemplateResponseDTO> sendMail(
//			@ApiParam(value = "mailType", required = false) @RequestParam("mailType") String mailType,
//			@RequestBody MailTemplateRequestDTO templateRequest) {
//		if (mailType == null) {
//			throw new SAPException("Unknown mail type");
//		}
//		MailTemplateData mailData = mailTemplateBuilder.buildMailTemplateData(templateRequest,
//				MailType.valueOf(mailType.toUpperCase()));
//		MailTemplateResponseDTO response = mailService.sendEmail(mailData);
//		if (response.getStatus().equals(MailStatus.ERROR)) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		return ResponseEntity.ok(response);
//	}

}
