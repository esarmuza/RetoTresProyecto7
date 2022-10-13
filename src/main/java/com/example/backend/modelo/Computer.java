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
@Table(name = "Computer") //Aqui se indica que Computer es una tabla
public class Computer implements Serializable {
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    //@Column(nullable = false )
    private Integer id;
    @Column(length =45 )
    private String name;
    @Column(length =45 )
    private String brand;
    @Column(name="YEARS")
    private Integer year;
   private String description;
    @ManyToOne
    @JoinColumn(name = "category")
    @JsonIgnoreProperties("computers")
    private Category category;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer" )
    @JsonIgnoreProperties({"computer","client"})
    private List<Message> messages;
    //Relacion con la tabla Reservation
    // El mappedBy = "computer" debe hacer referencia a la misma tabla
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer" )
    @JsonIgnoreProperties({"computer", "messages"})
    private List<Reservation> reservations;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
