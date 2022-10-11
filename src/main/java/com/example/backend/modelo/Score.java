package com.example.backend.modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
/*Con estas lineas hacemos que Java reconcozca la clase Score como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Score") //Aqui se indica que Score es una tabla
public class Score implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer idScore;
    private String messageText;
    private Integer stars;
    @OneToOne
    @JsonIgnoreProperties("score")
    private Reservation reservation;
    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}




