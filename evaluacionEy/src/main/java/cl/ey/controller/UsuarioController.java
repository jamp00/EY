package cl.ey.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ey.implement.UsuarioImplement;
import cl.ey.model.Usuario;

@RestController
@RequestMapping(value="/usuario/", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	private final String PREFIX = "Bearer ";
	private final String HEADER = "Authorization";

	@Autowired
	private UsuarioImplement usuarioImplement;

	// Crea registro Usuario
	@PostMapping("/registra")
	public ResponseEntity<Object> registroUsuario(HttpServletRequest request, @RequestBody @Valid Usuario usuario ) {
		
		if (usuarioImplement.existEmail(usuario.getEmail())) {

			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Correo ya registrado");
		}

		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		usuario.setToken(jwtToken);
		
		return new ResponseEntity<Object>(usuarioImplement.registraUsuario(usuario), HttpStatus.CREATED);

	}

}
