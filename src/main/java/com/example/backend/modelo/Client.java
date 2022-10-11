package com.example.backend.modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*Con estas lineas hacemos que Java reconcozca la clase Computer como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Client") //Aqui se indica que Computer es una tabla
public class Client implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//Definimos los campos de la tabla
    private Integer idClient;
    private String email;
    private String password;
    private String name;
    private Integer age;
    //Relacion con la tabla Message
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "client" )
    @JsonIgnoreProperties("client")
    private List<Message> messages;
    //Relacion con la tabla Reservation
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "client" )
    @JsonIgnoreProperties("client")
    private List<Reservation> reservations;
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
