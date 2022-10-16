package com.example.backend.controladoresWeb;
import com.example.backend.modelo.Reservation;
import com.example.backend.modelopersonal.CountClient;
import com.example.backend.modelopersonal.StatusAmount;
import com.example.backend.repository.ReservationRepository;
import com.example.backend.servicios.ReservationServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*ControlReservation es un REST Controler. Esta clase ControlReservation Gestionara las peticiones que se realicen
a la BD a la Tabla. Gestiona las peticiones que lleguen de Postman, del FrontEnd, de Apps Moviles entre otras.
 Esta clase la parte logica de la aplicacion que gestiona el acceso a la BD*/
@RestController
//Aqui se realiza el mapeo de la peticion
@RequestMapping("/api/Reservation")
//Se debe crrear un metodo por cada paticion a travez de ReservationServicio
/* Estas lineas es para no permitir realizar peticiones desde un sitio que no conocemos,desconocido */
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
@CrossOrigin ( origins = "*" , methods ={ RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE } )
public class ControlReservation {
    private ReservationRepository reservationRepository;
    /*Se crea un servicio. Auto Instanciacion @Autowired */
    @Autowired
    private ReservationServicio reservationServicio;

    //Funcion para obtener todos los computadores de la tabla
    /*Para que esta funcion se ejecute en una paticion GET con @GetMapping("/all")
    Con estas intruciones en el navegador podemos consultar por todos los Reservations con :
    en el navegador ( :NumeroPuerto/api/Reservation/all ) con esta direccion estaria llamando a este metodo
    getReservations() */
    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationServicio.getAll();
    }

    //Meetodo GET individual NO con el id sino directamente el numero. Ejemplo : /api/Reservation/8
    @GetMapping("/{id}")
    //El cuerpo de lapeticion dentra como una variable de la ruta, se emplea @PathVariable,
    // entonces debe atender lo que en la ruta es id
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationServicio.getReservationId(id);
    }

    @PostMapping("/save")
    /*Cuando se envian datos por el metodo POST, el metodo debe saber a travez de un parametro, que lo que envia el
    usuario esta en formato JSON, por tanto en el ingreso del parametro debe ser armado como tal para que
    CrudRepository interprete la peticion en el @RequestBody. Para indicar que el cuerpo de la peticion
    dentre por aqui se emplea @RequestBody.      Tambien indicamos el status que la peticion POST debe devolver */
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reserva) {
        return reservationServicio.save(reserva);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reserva) {
        return reservationServicio.update(reserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationServicio.deleteReservation(id);
    }
    //El cuerpo de lapeticion dentra como una variable de la ruta, se emplea @PathVariable,
    // entonces debe atender lo que en la ruta es id
    /*@GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return reservationServicio.reservaEntreFechas(dateOne, dateTwo);
    }*/
    //Codigo tutoria reto cinco Gabriela
    @GetMapping("/report-clients")
    public List<CountClient> getReservationReportClient(){
        return reservationServicio.getTopClients();
    }
    @GetMapping("/report-status")
    public StatusAmount getReservationStatus(){
        return reservationServicio.getReservationStatusReport();
    }
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getResevationReportDate(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo ){
        return reservationServicio.reservaEntreFechas(dateOne, dateTwo);
    }
}