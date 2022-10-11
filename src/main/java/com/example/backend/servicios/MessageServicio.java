package com.example.backend.servicios;
import com.example.backend.modelo.Message;
import com.example.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class MessageServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private MessageRepository MessageRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Message> getAll(){
        return MessageRepository.getAll();
    }
    public Optional<Message> getMessageId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Message sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return MessageRepository.getId(id);
    }
    public Message save(Message comp){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(comp.getId()==null){
            return MessageRepository.save(comp);
        }
        else{
            Optional<Message> computAux=MessageRepository.getId(comp.getId());
            //Aqui se valida si comp existe o no
            if(computAux.isEmpty()){
                return MessageRepository.save(comp);
            }
            else{
                return comp;
            }
        }
    }
    public Message update(Message mensaje ){
        if(mensaje.getId() !=null){
            Optional<Message> e =MessageRepository.getId (mensaje.getId());
            if(!e.isEmpty()){
                if(mensaje.getMessageText() !=null){
                    e.get().setMessageText(mensaje.getMessageText());
                }
                if(mensaje.getClient() !=null){
                    e.get().setClient(mensaje.getClient());
                }
                if(mensaje.getComputer() !=null){
                    e.get().setComputer(mensaje.getComputer());
                }
                 /*Si la salida es negativa o null */
                MessageRepository.save(e.get());
                return e.get();
            }
            else{
                return mensaje;
            }
        }else{
            return mensaje;
        }
    }
    public boolean deleteMessage(int id){
        Boolean CompuBoolean=getMessageId(id).map(Message -> {
            MessageRepository.delete(Message);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return CompuBoolean;
    }
}

