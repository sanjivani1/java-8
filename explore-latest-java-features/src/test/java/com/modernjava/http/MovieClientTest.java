package com.modernjava.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieClientTest {

    MovieClient movieClient = new MovieClient();

    @Test
    public void getMovieById(){

        var movie = movieClient.getMovieById();
        assertNotNull(movie);

        assertEquals("Christian Bale, Katie Holmes , Liam Neeson", movie.cast());
    }
}
