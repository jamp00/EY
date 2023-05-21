package cl.ey.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ey.config.JwtTokenUtil;
import cl.ey.model.JwtResponse;


@CrossOrigin
@RestController
@RequestMapping(value="/token", produces=MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

	private static final Logger logger = LogManager.getLogger(TokenController.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/")
	public ResponseEntity<?>  getToken() {
		logger.debug("Token");
		String token = jwtTokenUtil.getJWTToken("anonymous");
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
