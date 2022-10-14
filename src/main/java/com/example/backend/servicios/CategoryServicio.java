package com.example.backend.servicios;
import com.example.backend.modelo.Category;
import com.example.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class CategoryServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private CategoryRepository CategoryRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Category> getAll(){
        return CategoryRepository.getAll();
    }
    public Optional<Category> getCategoryId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Category sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return CategoryRepository.getId(id);
    }
    public Category save(Category catego){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(catego.getId()==null){
            return CategoryRepository.save(catego);
        }
        else{
            Optional<Category> categoAux=CategoryRepository.getId(catego.getId());
            //Aqui se valida si comp existe o no
            if(categoAux.isEmpty()){
                return CategoryRepository.save(catego);
            }
            else{
                return catego;
            }
        }
    }
    public Category update(Category categoria ){
        if(categoria.getId() !=null){
            Optional<Category> e =CategoryRepository.getId (categoria.getId());
            if(!e.isEmpty()){
                if(categoria.getName() !=null){
                    e.get().setName(categoria.getName());
                }
                if(categoria.getDescription() !=null){
                    e.get().setDescription(categoria.getDescription());
                }
                /*Si la salida es negativa o null */
                CategoryRepository.save(e.get());
                return e.get();
            }
            else{
                return categoria;
            }
        }else{
            return categoria;
        }
    }
    public boolean deleteCategory(int id){
        Boolean CategoBoolean=getCategoryId(id).map(Category -> {
            CategoryRepository.delete(Category);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return CategoBoolean;
    }
}