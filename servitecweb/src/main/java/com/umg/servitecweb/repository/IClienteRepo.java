package com.umg.servitecweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Long>{
	public List<Cliente> findByEstado(Integer e);
	public Optional<Cliente> findByCodigoCliente(String c);
	public Integer countByCodigoCliente(String c);
	public List<Cliente> findByEstadoAndNombreClienteContaining(Integer e, String search);
	public Integer countByCodigoClienteAndIdClienteNot(String c, Long id);
}
