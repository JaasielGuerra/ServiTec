package com.umg.servitecweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

}
