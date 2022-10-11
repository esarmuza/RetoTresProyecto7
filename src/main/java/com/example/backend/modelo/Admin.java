package com.example.backend.modelo;
import javax.persistence.*;
import java.io.Serializable;
/*Con estas lineas hacemos que Java reconcozca la clase Admin como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Admin") //Aqui se indica que Admin es una tabla
public class Admin implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer idAdmin;
    private String name;
    private String email;
    private String password;

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
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
}
