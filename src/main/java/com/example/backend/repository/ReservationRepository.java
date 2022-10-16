package com.example.backend.repository;
import com.example.backend.modelo.Client;
import com.example.backend.modelo.Reservation;
import com.example.backend.modelopersonal.CountClient;
import com.example.backend.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//Para que esea un Repository se debe indicar con @Repository, es
// un Repository es un componente de spring que permite realizar
// transacciones con la BD, permite llevar  traer informacion desde y hacia la BD,
@Repository
public class ReservationRepository {
    //Para que el Framework Spring sea el que inicialice este objeto, se utiliza la eetiqueeta
    // @Autowired, y solo se utiliza dentro de componentes de Spring.
    //En esta clase es donde se definen todos los metodos para interactuar con la BD
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    public List<Reservation> getAll(){
        //CrudRepository tiene una gran cantidad de metodos par interactuar con la BD
        //Se hace pasar a Comoputer por una lista
        return ( List<Reservation>) reservationCrudRepository.findAll();
    }
    /*Optional es un tipo de Objeto de java, que si hay informacion en la BD la entrega y si
     no la hay  entrega el objeto aunque no haya encontrado algo en la BD */
    public Optional<Reservation> getId(int id){
        return reservationCrudRepository.findById(id);
    }
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    public List<Reservation> getReservationPeriod(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a,b);
    }
    //Codigo tutoria reto cinco gabriela 15/10/2022
    public List<CountClient> getTopClients(){
        List<CountClient> arrayClientTop= new ArrayList<>();
        List<Object[]> report=reservationCrudRepository.countTotalReservationByClient();
        for(int  i=0;i>report.size(); i++){
            arrayClientTop.add(new CountClient((Long)report.get(i)[1], (Client)report.get(i)[0]));
        }
        return arrayClientTop;
    }
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }
}