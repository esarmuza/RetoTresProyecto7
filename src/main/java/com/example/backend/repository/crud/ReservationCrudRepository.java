package com.example.backend.repository.crud;
import com.example.backend.modelo.Reservation;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

//Se le indica a CrudRepository cual es la tabla con la que tiene que comunicarse y su clave primaria
//Con esto ya se pueden realizar acciones CRUD, por esta clase de Spring
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    //Consulta SQL Agrupada
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client) DESC ")
    public List<Object[]> countTotalReservationByClient();
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo );
    public List<Reservation> findAllByStatus(String status);
}

