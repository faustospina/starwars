package com.porvenirms.starwars.services;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;

public interface StarwarsService {
    StarwarsDTO getFilms(String id) throws PorvenirBussinesException;
}
