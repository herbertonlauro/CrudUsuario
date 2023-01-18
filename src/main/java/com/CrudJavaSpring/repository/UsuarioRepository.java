package com.CrudJavaSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CrudJavaSpring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
