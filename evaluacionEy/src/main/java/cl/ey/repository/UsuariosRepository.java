package cl.ey.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.ey.model.Usuarios;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Serializable> {

	public Usuarios findByEmail(String email);

}

