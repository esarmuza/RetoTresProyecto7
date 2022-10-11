package com.example.backend.repository.crud;
import com.example.backend.modelo.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

//Se le indica a CrudRepository cual es la tabla con la que tiene que comunicarse y su clave primaria
//Con esto ya se pueden realizar acciones CRUD, por esta clase de Spring
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date
            dateTwo );
}

