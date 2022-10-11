package com.example.backend.repository.crud;

import com.example.backend.modelo.Message;
import org.springframework.data.repository.CrudRepository;
//Se le indica a CrudRepository cual es la tabla con la que tiene que comunicarse y su clave primaria
//Con esto ya se pueden realizar acciones CRUD, por esta clase de Spring
public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}