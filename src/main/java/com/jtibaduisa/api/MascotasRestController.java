package com.jtibaduisa.api;

import com.jtibaduisa.entity.Mascotas;
import com.jtibaduisa.respository.IMascostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotasRestController {

    @Autowired
    private IMascostasRepository iMascotas;

    @GetMapping
    @Cacheable(value = "mascotas")
    public List<Mascotas> getAllMascotas() {
        Iterator<Mascotas> iterator = iMascotas.findAll().iterator();
        List<Mascotas> mascotas = new ArrayList<Mascotas>();
        while(iterator.hasNext()){
            mascotas.add(iterator.next());
        }
        return mascotas;
    }

    @GetMapping(path = "/{idMascota}")
    @Cacheable( value = "mascota", key = "#idMascota")
    public Optional<Mascotas> getByIdMascota(@PathVariable String idMascota) {
        System.out.println("Consultando en elasticsearch");
        return iMascotas.findById(idMascota);
    }

    @PostMapping()
    public Mascotas createMascota(@RequestBody Mascotas mascota) {
        return iMascotas.save( mascota );
    }

    @PutMapping( path = "/{idMascota}")
    @CachePut( value = "mascota", key = "#idMascota" )
    public Mascotas updateMascota(@PathVariable String idMascota,@RequestBody Mascotas mascota) {
        Optional<Mascotas> mascota_search = iMascotas.findById(idMascota);
        if (mascota_search.isPresent()) {
            Mascotas m = mascota_search.get();
            m.setNombre (mascota.getNombre());
            m.setRaza (mascota.getRaza());
            m.setColor (mascota.getColor());
            m.setEdad (mascota.getEdad());
            return iMascotas.save(m);
        }
        else {
            return null;
        }
    }

    @DeleteMapping( path = "/{idMascota}" )
    @CacheEvict( value = "mascota", allEntries = true )
    public String deleteMascota(@PathVariable String idMascota) {
        Optional<Mascotas> mascota_search = iMascotas.findById(idMascota);
        if (mascota_search.isPresent()) {
            iMascotas.deleteById(idMascota);
            return "Mascota con referencia " + idMascota + " eliminado correctamente";
        } else {
            return "Error al eliminar la mascota con referencia " + idMascota;
        }
    }

}
