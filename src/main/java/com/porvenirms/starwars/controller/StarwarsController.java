package com.porvenirms.starwars.controller;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;
import com.porvenirms.starwars.services.StarwarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api/starwars")
@RestController
public class StarwarsController {

    @Autowired
    private StarwarsService service;

    @GetMapping(path = "/films/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchFilm(@PathVariable String id){
        try{
            return new ResponseEntity<>(service.getFilms(id), HttpStatus.OK);
        }catch (PorvenirBussinesException e){
            return buildResponse(e);
        }

    }

    @PutMapping(path = "/films/edit/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateFilm(@PathVariable Integer id, @RequestBody StarwarsDTO request){
        try{
            return new ResponseEntity<>(service.updateFilmDataBase(id,request), HttpStatus.OK);
        }catch (PorvenirBussinesException e){
            return buildResponse(e);
        }

    }


    private ResponseEntity buildResponse(PorvenirBussinesException response){
        ResponseEntity responseEntity = null;
        HttpStatus status = getHttpStatus(response.getStatus());
        switch (status){
            case NO_CONTENT -> responseEntity.noContent().build();
            case BAD_REQUEST ->responseEntity = new ResponseEntity<>(response,status);
            case NOT_FOUND -> responseEntity = new ResponseEntity<>(response,status);
        }
        return responseEntity;
    }

    private HttpStatus getHttpStatus(int cod){
        return HttpStatus.valueOf(cod);
    }
}
