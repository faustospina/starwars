package com.porvenirms.starwars.services;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;

public interface SwapiService {

    SwapiFilm getFilm(String id) throws PorvenirBussinesException;
}
