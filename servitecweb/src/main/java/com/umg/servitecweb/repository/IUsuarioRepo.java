package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findByEstado(Integer estado);
}
