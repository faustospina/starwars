package com.porvenirms.starwars.services;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;

public interface StarwarsService {
    StarwarsDTO getFilms(String id) throws PorvenirBussinesException;

    StarwarsDTO updateFilmDataBase(Integer id, StarwarsDTO dto) throws PorvenirBussinesException;
}
