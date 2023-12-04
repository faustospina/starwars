package com.porvenirms.starwars.services;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;
import com.porvenirms.starwars.models.entities.StarwarsEntity;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;
import com.porvenirms.starwars.repository.StarwarsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsServiceImplTest {

    @InjectMocks
    private StarWarsServiceImpl starWarsService;

    @Mock
    private SwapiService swapiService;

    @Mock
    private StarwarsRepository repository;

    @Test
    public void testGetFilms() throws PorvenirBussinesException {
        StarwarsEntity existingEntity = new StarwarsEntity();
        String filmId = "1";
        SwapiFilm expectedSwapiFilm = SwapiFilm.builder().release_date(Date.valueOf("1977-05-25")).title("Star Wars: Episode IV").build();
        when(swapiService.getFilm(filmId)).thenReturn(expectedSwapiFilm);
        when(repository.save(Mockito.any())).thenReturn(existingEntity);
        starWarsService.getFilms(filmId);
        verify(swapiService, times(1)).getFilm(filmId);
    }



    @Test(expected = PorvenirBussinesException.class)
    public void testGetFilmsException() throws PorvenirBussinesException {
        String filmId = "1";
        when(swapiService.getFilm(filmId)).thenThrow(new PorvenirBussinesException(1, "Not found", 404));
        starWarsService.getFilms(filmId);
    }

    @Test(expected = PorvenirBussinesException.class)
    public void testGetFilmsExceptionValidId() throws PorvenirBussinesException {
        String filmId = "109";
        starWarsService.getFilms(filmId);
    }

    @Test(expected = PorvenirBussinesException.class)
    public void testGetFilmsExceptionValidIdNull() throws PorvenirBussinesException {
        starWarsService.getFilms(null);
    }

    @Test
    public void testUpdateFilmDataBase() throws PorvenirBussinesException {
        // Mocking behavior of repository
        int entityId = 1;
        StarwarsEntity existingEntity = new StarwarsEntity();
        when(repository.findById(entityId)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(existingEntity);

        // Call the method being tested
        StarwarsDTO dto = StarwarsDTO.builder().title("Updated Title").build();
        starWarsService.updateFilmDataBase(entityId, dto);

        // Verify that the method under test behaves as expected
        verify(repository, times(1)).findById(entityId);
        verify(repository, times(1)).save(existingEntity);
        // Add any additional assertions based on your requirements
    }

    @Test(expected = PorvenirBussinesException.class)
    public void testUpdateFilmDataBaseNotFound() throws PorvenirBussinesException {
        // Mocking behavior of repository to simulate entity not found
        int entityId = 1;
        when(repository.findById(entityId)).thenReturn(Optional.empty());

        // Call the method being tested
        StarwarsDTO dto = StarwarsDTO.builder().title("Updated Title").build();
        starWarsService.updateFilmDataBase(entityId, dto);
    }
}