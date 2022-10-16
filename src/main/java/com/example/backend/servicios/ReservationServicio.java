package com.example.backend.servicios;
import com.example.backend.modelo.Client;
import com.example.backend.modelo.Reservation;
import com.example.backend.modelopersonal.CountClient;
import com.example.backend.modelopersonal.StatusAmount;
import com.example.backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/*Service es un componente de Spring Service hace de filtro de logica de negocio. Tambien
para reutilizar datos*/
@Service
public class ReservationServicio {
    /*Aqui va la logica del negocio, aqui se realizan los llamados a las acciones,
    es es que se encarga de entenderse con el Yearo Insntanciamos el objeto */
    @Autowired
    private ReservationRepository reservationRepository;
    /*Servicios hace de puente entre el Yearo y el crud. Esta lista es de tipo java.util.List; */
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getReservationId(int id){
        /*Con Optional sucede lo siguienete: Cuando llegue un Reservation sin id  indica que no esta en la BD
         y se guardara, si llega con id se valida que no exista en la BD ese id, y se guarda.
         Si ese id ya existe se devuelve  y no se guarda  */
        return reservationRepository.getId(id);
    }
    public Reservation save(Reservation reserva){
        //Si id es null entonces el id, el coomputer es nuevo,entonces guardelo
        if(reserva.getIdReservation()==null){
            return reservationRepository.save(reserva);
        }
        else{
            Optional<Reservation> reservAux=reservationRepository.getId(reserva.getIdReservation());
            //Aqui se valida si comp existe o no
            if(reservAux.isPresent()){
                return reservationRepository.save(reserva);
            }
            else{
                return reserva;
            }
        }
    }
    public Reservation update(Reservation reservacion ){
        if(reservacion.getIdReservation() !=null){
            Optional<Reservation> e =reservationRepository.getId (reservacion.getIdReservation());
            if(e.isPresent()){
                if(reservacion.getStartDate() !=null){
                    e.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate() !=null){
                    e.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getClient() !=null){
                    e.get().setClient(reservacion.getClient());
                }
                if(reservacion.getComputer() !=null){
                    e.get().setComputer(reservacion.getComputer());
                }
                /*Si la salida es negativa o null */
                reservationRepository.save(e.get());
                return e.get();
            }
            else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }
    public boolean deleteReservation(int id){
        Boolean ReserBoolean=getReservationId(id).map(Reservation -> {
            reservationRepository.delete(Reservation);
            return true;
        }).orElse(Boolean.FALSE);  //.orElse(other:false);
        return ReserBoolean;
    }
    public List<Reservation> reservaEntreFechas(String fechaUno, String fechaDos){
        //Aquí obtienes el formato que deseas
        SimpleDateFormat yearMesDia = new SimpleDateFormat("yyyy-MM-dd");  //HH:mm:ss.SSS
        //System.out.println(java.time.LocalDateTime.now());
        Date inicio=new Date();
        Date fin=new Date();
        try{
            inicio = yearMesDia.parse(fechaUno);
            fin = yearMesDia.parse(fechaDos);
        } catch (ParseException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        if(inicio.before(fin)){
            return reservationRepository.getReservationPeriod(inicio, fin);
        }else {
            return new ArrayList<>();
        }
    }
    //Codigo tutoria reto Cinco gabriela 15/10/2022
    public StatusAmount getReservationStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled=reservationRepository.getReservationByStatus("cancelled");
        return new StatusAmount(completed.size(), cancelled.size());
    }
    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClients();
    }
}
