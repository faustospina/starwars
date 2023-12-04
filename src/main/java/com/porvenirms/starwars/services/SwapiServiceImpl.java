package com.porvenirms.starwars.services;

import com.porvenirms.starwars.client.SwapiClient;
import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwapiServiceImpl implements SwapiService {

    @Autowired
    private SwapiClient swapiClient;


    @Override
    public SwapiFilm getFilm(String id) throws PorvenirBussinesException {
        try {
            return swapiClient.getFilmById(id);
        } catch (Exception ex) {
            throw new PorvenirBussinesException(1,"Not found",204);
        }
    }

}
