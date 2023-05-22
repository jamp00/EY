package cl.ey.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ey.model.Usuario;
import cl.ey.repository.UsuariosRepository;

@Service
public class UsuarioImplement {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	public Usuario registraUsuario(Usuario usuario ) {
		return usuariosRepository.save(usuario);
	}

	public boolean existEmail(String email) {

		Usuario usuario = usuariosRepository.findByEmail(email);

		return Optional.ofNullable(usuario).isPresent();

	}
	
}
