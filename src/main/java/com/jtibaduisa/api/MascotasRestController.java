package com.jtibaduisa.api;

import com.jtibaduisa.entity.Mascotas;
import com.jtibaduisa.respository.IMascostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping( "/api/v1/mascotas" )
public class MascotasRestController {

    @Autowired
    private IMascostasRepository iMascotas;

    @GetMapping
    public List<Mascotas> getAllMascotas() {
        Iterator<Mascotas> iterator = iMascotas.findAll().iterator();
        List<Mascotas> mascotas = new ArrayList<Mascotas>();
        while(iterator.hasNext()){
            mascotas.add(iterator.next());
        }
        return mascotas;
    }
}
