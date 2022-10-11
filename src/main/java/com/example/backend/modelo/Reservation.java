package com.example.backend.modelo;
 import com.example.backend.servicios.ReservationServicio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/*Con estas lineas hacemos que Java reconcozca la clase Reservation como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Reservation") //Aqui se indica que Reservation es una tabla

public class Reservation implements Serializable {
     //Le indicamos a java que la llave primaria es el id
     @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer idReservation;
    private Date startDate;
     private Date devolutionDate;
     private String status;
     //Relacion con Computer
    @ManyToOne
    @JoinColumn(name = "computer")
    @JsonIgnoreProperties("reservations")
    private Computer computer;
    //Relacion a tabla Client
    @ManyToOne
    @JoinColumn(name = "client")
    @JsonIgnoreProperties({"reservations", "messages"})
    private Client client;
    //Relacion tabla Score
    @OneToOne
    @JoinColumn(name = "score",nullable = true)
    @JsonIgnoreProperties("reservation")
    private Score score;

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
