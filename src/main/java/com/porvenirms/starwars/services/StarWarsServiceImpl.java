package com.porvenirms.starwars.services;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;
import com.porvenirms.starwars.models.entities.StarwarsEntity;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;
import com.porvenirms.starwars.repository.StarwarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class StarWarsServiceImpl implements StarwarsService{

    @Autowired
    private SwapiService swapiService;


    @Autowired
    private StarwarsRepository repository;

    @Override
    public StarwarsDTO getFilms(String id) throws PorvenirBussinesException {
        try{
            validId(id);
            StarwarsEntity entityOut = buildEntity(id);
            return StarwarsDTO
                     .builder()
                     .title(entityOut.getTitle())
                     .release_date(entityOut.getRelease_date())
                     .episode_id(entityOut.getEpisode_id()).build();
        }catch (PorvenirBussinesException ex) {
           throw new PorvenirBussinesException(1, "Not found", 204);
        }catch (IllegalArgumentException ex){
            throw new PorvenirBussinesException(1, "error en la solicitud", 400);
        }
    }

    @Override
    public StarwarsDTO updateFilmDataBase(Integer id, StarwarsDTO dto) throws PorvenirBussinesException {
        StarwarsEntity entity = repository.findById(id).orElseThrow(()->new PorvenirBussinesException(1,"Not found",404));
        updateIfChange(dto.getTitle(),entity::setTitle);
        updateIfChange(dto.getEpisode_id(),entity::setEpisode_id);
        updateIfChange(dto.getRelease_date(),entity::setRelease_date);
        StarwarsEntity entityOut = repository.save(entity);
        return StarwarsDTO
                .builder()
                .title(entityOut.getTitle())
                .release_date(entityOut.getRelease_date())
                .episode_id(entityOut.getEpisode_id()).build();
    }

    private <T> void updateIfChange(T nuevoValor, Consumer<T> updater){
        Optional.ofNullable(nuevoValor)
                .filter(valor->!valor.equals(updater))
                .ifPresent(updater);
    }

    private StarwarsEntity buildEntity(String id) throws PorvenirBussinesException {
        SwapiFilm temp = swapiService.getFilm(id);
        StarwarsEntity entity = new StarwarsEntity();
        entity.setTitle(temp.getTitle());
        entity.setEpisode_id(temp.getEpisode_id());
        entity.setRelease_date(temp.getRelease_date());
        StarwarsEntity entityOut = repository.save(entity);
        return entityOut;
    }
    private void validId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        if (id.length() > 2) {
            throw new IllegalArgumentException("El ID no puede ser más largo que 2 caracteres");
        }
    }

}
