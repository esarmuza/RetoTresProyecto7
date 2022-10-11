package com.example.backend.servicios;
import com.example.backend.modelo.Client;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class ClientServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private ClientRepository ClientRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Client> getAll(){
        return ClientRepository.getAll();
    }
    public Optional<Client> getClientId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Client sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return ClientRepository.getId(id);
    }
    public Client save(Client client){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(client.getIdClient()==null){
            return ClientRepository.save(client);
        }
        else{
            Optional<Client> computAux=ClientRepository.getId(client.getIdClient());
            //Aqui se valida si comp existe o no
            if(computAux.isEmpty()){
                return ClientRepository.save(client);
            }
            else{
                return client;
            }
        }
    }
    public Client update(Client cliente ){
        if(cliente.getIdClient() !=null){
            Optional<Client> e =ClientRepository.getId (cliente.getIdClient());
            if(!e.isEmpty()){
                if(cliente.getName() !=null){
                    e.get().setName(cliente.getName());
                }
                if(cliente.getEmail() !=null){
                    e.get().setEmail(cliente.getEmail());
                }
                if(cliente.getPassword() !=null){
                    e.get().setPassword(cliente.getPassword());
                }
                if(cliente.getAge() !=null){
                    e.get().setAge(cliente.getAge());
                }
                /*Si la salida es negativa o null */
                ClientRepository.save(e.get());
                return e.get();
            }
            else{
                return cliente;
            }
        }else{
            return cliente;
        }
    }
    public boolean deleteClient(int id){
        Boolean ClientBoolean=getClientId(id).map(Client -> {
            ClientRepository.delete(Client);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return ClientBoolean;
    }
}