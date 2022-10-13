package com.example.backend.modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category") //Aqui se indica que Category es una tabla
/*ESTE ES UN COMOENTARIO CLASE CATEGORY*/
public class Category implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer id;
    private String name;
    private String description;

    //Se establecen las relaciones con la tabla Computer.
    // Se indica que si se modifica algo en una tabla continue con ascadeType.PERSIST
    //Se le indica que cada uno de los Computers lo va ha encontrar con el campo categoriaId
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Computer> computers;

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Computer> getComputers() {
        return computers;
    }
    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }
}