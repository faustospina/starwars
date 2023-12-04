package com.porvenirms.starwars.controller;

import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.dto.StarwarsDTO;
import com.porvenirms.starwars.services.StarwarsService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StarwarsControllerTest {


    @Mock
    private StarwarsService starwarsService;

    @InjectMocks
    private StarwarsController starwarsController;

    @Test
    public void testSearchFilm() throws Exception {
        StarwarsDTO starwarsDTO = new StarwarsDTO();
        starwarsDTO.setEpisode_id(4);
        starwarsDTO.setTitle("la amenaza fantasma");
        starwarsDTO.setRelease_date(Date.valueOf("1977-05-25"));
        when(starwarsService.getFilms("1")).thenReturn(starwarsDTO);
        ResponseEntity<Object> response = starwarsController.searchFilm("1");
        Assertions.assertNotNull(response);
    }

    @Test
    public void testSearchFilmException404() throws Exception {
        PorvenirBussinesException exception = new PorvenirBussinesException(1,"test",404);
        when(starwarsService.getFilms("1")).thenThrow(exception);
        ResponseEntity<Object> response = starwarsController.searchFilm("1");
        Assertions.assertNotNull(response);
    }

    @Test
    public void testSearchFilmException204() throws Exception {
        PorvenirBussinesException exception = new PorvenirBussinesException(1,"test",204);
        when(starwarsService.getFilms("1")).thenThrow(exception);
        ResponseEntity<Object> response = starwarsController.searchFilm("1");
        Assertions.assertNull(response);
    }

    @Test
    public void testSearchFilmException400() throws Exception {
        PorvenirBussinesException exception = new PorvenirBussinesException(1,"test",400);
        when(starwarsService.getFilms("1")).thenThrow(exception);
        ResponseEntity<Object> response = starwarsController.searchFilm("1");
        Assertions.assertNotNull(response);
    }

    @Test
    public void testUpdateFilmException() throws PorvenirBussinesException {
        // Mocking behavior of StarwarsService
        int filmId = 1;
        StarwarsDTO requestDTO = StarwarsDTO.builder().title("Updated Title").build();
        StarwarsDTO expectedDTO = StarwarsDTO.builder().title("Updated Title").build();
        PorvenirBussinesException exception = new PorvenirBussinesException(1,"test",404);
        when(starwarsService.updateFilmDataBase(eq(filmId), any())).thenThrow(exception);


        // Call the method being tested
        ResponseEntity<Object> response = starwarsController.updateFilm(filmId, requestDTO);
        Assertions.assertNotNull(response);
    }

    @Test
    public void testUpdateFilmSuccess() throws PorvenirBussinesException {
        // Mocking behavior of StarwarsService
        int filmId = 1;
        StarwarsDTO requestDTO = StarwarsDTO.builder().title("Updated Title").build();
        StarwarsDTO expectedDTO = StarwarsDTO.builder().title("Updated Title").build();
        when(starwarsService.updateFilmDataBase(eq(filmId), any())).thenReturn(expectedDTO);

        // Call the method being tested
        ResponseEntity<Object> responseEntity = starwarsController.updateFilm(filmId, requestDTO);

        // Verify that the method under test behaves as expected
        verify(starwarsService, times(1)).updateFilmDataBase(eq(filmId), any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDTO, responseEntity.getBody());
    }


}

