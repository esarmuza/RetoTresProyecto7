package com.example.backend.controladoresWeb;
import com.example.backend.modelo.Computer;
import com.example.backend.servicios.ComputerServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/*ControlComputer es un REST Controler. Esta clase ControlComputer Gestionara las peticiones que se realicen
a la BD a la Tabla. Gestiona las peticiones que lleguen de Postman, del FrontEnd, de Apps Moviles entre otras.
 Esta clase la parte logica de la aplicacion que gestiona el acceso a la BD*/
@RestController
//Aqui se realiza el mapeo de la peticion
@RequestMapping("/api/Computer")
//Se debe crrear un metodo por cada paticion a travez de ComputerServicio
/* Estas lineas es para no permitir realizar peticiones desde un sitio que no conocemos,desconocido */
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
@CrossOrigin ( origins = "*" , methods ={ RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE } )

public class ControlComputer {
    /*Se crea un servicio. Auto Instanciacion @Autowired */
    @Autowired
    private ComputerServicio computerServicio;
    //Funcion para obtener todos los computadores de la tabla
    /*Para que esta funcion se ejecute en una paticion GET con @GetMapping("/all")
    Con estas intruciones en el navegador podemos consultar por todos los Computers con :
    en el navegador ( :NumeroPuerto/api/Computer/all ) con esta direccion estaria llamando a este metodo
    getComputers() */
    @GetMapping("/all")
    public List<Computer> getComputers(){
        return computerServicio.getAll();
    }
    //Meetodo GET individual NO con el id sino directamente el numero. Ejemplo : /api/Computer/8
    @GetMapping("/{id}")
    //El cuerpo de lapeticion dentra como una variable de la ruta, se emplea @PathVariable,
    // entonces debe atender lo que en la ruta es id
    public Optional<Computer> getComputer(@PathVariable ("id") int id ){
        return computerServicio.getComputerId(id);
    }
    @PostMapping("/save")
    /*Cuando se envian datos por el metodo POST, el metodo debe saber a travez de un parametro, que lo que envia el
    usuario esta en formato JSON, por tanto en el ingreso del parametro debe ser armado como tal para que
    CrudRepository interprete la peticion en el @RequestBody. Para indicar que el cuerpo de la peticion
    dentre por aqui se emplea @RequestBody
     Tabien indicamos el status que la peticion POST debe devolver */
    @ResponseStatus(HttpStatus.CREATED)
    public Computer save( @RequestBody Computer comp){
        return computerServicio.save(comp);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer update( @RequestBody Computer comp){
        return computerServicio.update(comp);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete( @PathVariable("id") int id){
        return computerServicio.deleteComputer(id);
    }
}
