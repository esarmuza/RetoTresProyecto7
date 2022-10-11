package com.example.backend.servicios;
import com.example.backend.modelo.Admin;
import com.example.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class AdminServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private AdminRepository AdminRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Admin> getAll(){
        return AdminRepository.getAll();
    }
    public Optional<Admin> getAdminId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Admin sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return AdminRepository.getId(id);
    }
    public Admin save(Admin serv){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(serv.getIdAdmin()==null){
            return AdminRepository.save(serv);
        }
        else{
            Optional<Admin> adminAux=AdminRepository.getId(serv.getIdAdmin());
            //Aqui se valida si comp existe o no
            if(adminAux.isEmpty()){
                return AdminRepository.save(serv);
            }
            else{
                return serv;
            }
        }
    }
    public Admin update(Admin  administrador ){
        if(administrador.getIdAdmin() !=null){
            Optional<Admin> e =AdminRepository.getId (administrador.getIdAdmin());
            if(!e.isEmpty()){
                if(administrador.getName() !=null){
                    e.get().setName(administrador.getName());
                }
                /*Si la salida es negativa o null */
                AdminRepository.save(e.get());
                return e.get();
            }
            else{
                return administrador;
            }
        }else{
            return administrador;
        }
    }
    public boolean deleteAdmin(int id){
        Boolean AdmiBoolean=getAdminId(id).map(Admin -> {
            AdminRepository.delete(Admin);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return AdmiBoolean;
    }
}
