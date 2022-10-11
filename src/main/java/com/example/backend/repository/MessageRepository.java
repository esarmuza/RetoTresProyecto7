package com.example.backend.repository;
import com.example.backend.modelo.Message;
import com.example.backend.repository.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
//Para que esea un Repository se debe indicar con @Repository, es
// un Repository es un componente de spring que permite realizar
// transacciones con la BD, permite llevar  traer informacion desde y hacia la BD,
@Repository
public class MessageRepository {
    //Para que el Framework Spring sea el que inicialice este objeto, se utiliza la eetiqueeta
    // @Autowired, y solo se utiliza dentro de componentes de Spring.
    //En esta clase es donde se definen todos los metodos para interactuar con la BD
    @Autowired
    private MessageCrudRepository MessageCrudRepository;
    public List<Message> getAll(){
        //CrudRepository tiene una gran cantidad de metodos par interactuar con la BD
        //Se hace pasar a Comoputer por una lista
        return ( List<Message>) MessageCrudRepository.findAll();
    }
    /*Optional es un tipo de Objeto de java, que si hay informacion en la BD la entrega y si
     no la hay  entrega el objeto aunque no haya encontrado algo en la BD */
    public Optional<Message> getId(int id){
        return MessageCrudRepository.findById(id);
    }
    public Message save(Message Message){
        return MessageCrudRepository.save(Message);
    }
    public void delete(Message Message){
        MessageCrudRepository.delete(Message);
    }

}