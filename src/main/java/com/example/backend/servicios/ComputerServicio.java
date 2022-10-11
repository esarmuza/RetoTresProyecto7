package com.example.backend.servicios;
import com.example.backend.modelo.Computer;
import com.example.backend.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class ComputerServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private ComputerRepository computerRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Computer> getAll(){
        return computerRepository.getAll();
    }
    public Optional<Computer> getComputerId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Computer sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return computerRepository.getId(id);
    }
    public Computer save(Computer comp){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(comp.getId()==null){
            return computerRepository.save(comp);
        }
        else{
            Optional<Computer> computAux=computerRepository.getId(comp.getId());
            //Aqui se valida si comp existe o no
            if(computAux.isEmpty()){
                return computerRepository.save(comp);
            }
            else{
                return comp;
            }
        }
    }
    public Computer update(Computer computador ){
        if(computador.getId() !=null){
            Optional<Computer> e =computerRepository.getId (computador.getId());
            if(!e.isEmpty()){
                if(computador.getBrand() !=null){
                    e.get().setBrand(computador.getBrand());
                }
                if(computador.getYear() !=null){
                    e.get().setYear(computador.getYear());
                }
                if(computador.getCategory() !=null){
                    e.get().setCategory(computador.getCategory());
                }
                if(computador.getName() !=null){
                    e.get().setName(computador.getName());
                }
                /*Si la salida es negativa o null */
                computerRepository.save(e.get());
                return e.get();
            }
            else{
                return computador;
            }
        }else{
            return computador;
        }
    }
    public boolean deleteComputer(int id){
        Boolean CompuBoolean=getComputerId(id).map(computer -> {
            computerRepository.delete(computer);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return CompuBoolean;
    }
}
