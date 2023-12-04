package com.porvenirms.starwars.client;

import com.porvenirms.starwars.models.feignmodel.SwapiFilm;
import feign.Param;
import feign.RequestLine;

public interface SwapiClient {
    @RequestLine("GET /films/{id}/")
    SwapiFilm getFilmById(@Param("id") String id);
}
