package com.example.backend.servicios;
import com.example.backend.modelo.Score;
import com.example.backend.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class ScoreServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private ScoreRepository ScoreRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Score> getAll(){
        return ScoreRepository.getAll();
    }
    public Optional<Score> getScoreId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Score sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return ScoreRepository.getId(id);
    }
    public Score save(Score comp){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(comp.getIdScore()==null){
            return ScoreRepository.save(comp);
        }
        else{
            Optional<Score> computAux=ScoreRepository.getId(comp.getIdScore());
            //Aqui se valida si comp existe o no
            if(computAux.isPresent()){
                return ScoreRepository.save(comp);
            }
            else{
                return comp;
            }
        }
    }
    public Score update(Score premio ){
        if(premio.getIdScore() !=null){
            Optional<Score> e =ScoreRepository.getId (premio.getIdScore());
            if(e.isPresent()){
                if(premio.getMessageText() !=null){
                    e.get().setMessageText(premio.getMessageText());
                }
                if(premio.getStars() !=null){
                    e.get().setStars(premio.getStars());
                }
                /*Si la salida es negativa o null */
                ScoreRepository.save(e.get());
                return e.get();
            }
            else{
                return premio;
            }
        }else{
            return premio;
        }
    }
    public boolean deleteScore(int id){
        Boolean ScorBoolean=getScoreId(id).map(Score -> {
            ScoreRepository.delete(Score);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return ScorBoolean;
    }
}
