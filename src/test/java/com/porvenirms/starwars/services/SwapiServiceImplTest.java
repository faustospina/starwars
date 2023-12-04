package com.porvenirms.starwars.services;

import com.porvenirms.starwars.client.SwapiClient;
import com.porvenirms.starwars.exception.PorvenirBussinesException;
import com.porvenirms.starwars.models.feignmodel.SwapiFilm;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SwapiServiceImplTest {
    @InjectMocks
    private SwapiServiceImpl swapiService;

    @Mock
    private SwapiClient swapiClient;

    @Test
    public void testGetFilmSuccess() throws PorvenirBussinesException {
        String filmId = "1";
        SwapiFilm expectedFilm = SwapiFilm.builder().title("Star Wars: Episode IV").build();
        when(swapiClient.getFilmById(filmId)).thenReturn(expectedFilm);
        SwapiFilm result = swapiService.getFilm(filmId);
        Assertions.assertNotNull(result);
        verify(swapiClient, times(1)).getFilmById(filmId);
    }
    @Test
   public void testGetFilmException(){
        try{
            String filmId = "1";
            when(swapiClient.getFilmById(filmId)).thenThrow(new RuntimeException("Test Exception"));swapiService.getFilm(filmId);
        }catch (PorvenirBussinesException e){
            Assertions.assertNotNull(e);
        }
    }
}