package com.modernjava.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MovieClient {

    HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();

    public final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public static String all_movies = "http://127.0.0.1:8000/explore-latest-java-features/src/main/Resources/movies.json";

    public static String movies_by_id = "http://127.0.0.1:8000/explore-latest-java-features/src/main/Resources/movie_by_id.json";

    public Movie getMovieById(){

        var request = requestBuilder(movies_by_id);
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("status Code: "+response.statusCode());
            return objectMapper.readValue(response.body(), Movie.class);
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    public HttpRequest requestBuilder(String url){

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

}
