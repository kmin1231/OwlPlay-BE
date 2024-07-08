package io.owl_browthers.owlplay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.owl_browthers.owlplay.entity.Movie;
import io.owl_browthers.owlplay.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Iterator;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    private final RestTemplate restTemplate;
    private final String apiKey = "d9cfefb6e49016077fbd0c2a20c8dbb8";
    private final ObjectMapper objectMapper;

    public MovieService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void fetchAndSaveMovies(int year) {
        String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=" + apiKey +
                "&openStartDt=" + year + "&openEndDt=" + year + "&itemPerPage=100&curPage=";
        int curPage = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            String response = restTemplate.getForObject(url + curPage, String.class);
            try {
                JsonNode root = objectMapper.readTree(response);
                JsonNode movieListResult = root.path("movieListResult");
                JsonNode movieList = movieListResult.path("movieList");

                if (!movieList.isArray() || !movieList.elements().hasNext()) {
                    hasNextPage = false;
                } else {
                    Iterator<JsonNode> elements = movieList.elements();
                    while (elements.hasNext()) {
                        JsonNode movieJson = elements.next();
                        String openDt = movieJson.path("openDt").asText();
                        int movieYear = Integer.parseInt(openDt.substring(0, 4));
                        if (movieYear == 2023 || movieYear == 2024) {
                            Movie movie = new Movie();
                            movie.setMovieCd(movieJson.path("movieCd").asText());
                            movie.setMovieNm(movieJson.path("movieNm").asText());
                            movie.setOpenDt(LocalDate.parse(openDt));
                            movie.setDirectors(movieJson.path("directors").path(0).path("peopleNm").asText());
                            movieRepository.save(movie);
                        }
                    }
                    curPage++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                hasNextPage = false;
            }
        }
    }
    public void fetchAndSaveMovieDetails() {
        Iterable<Movie> moviesIterable = movieRepository.findAll();
        moviesIterable.forEach(this::fetchMovieDetailsAndUpdate);
    }

    private void fetchMovieDetailsAndUpdate(Movie movie) {
        String url = String.format(
                "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=%s&movieCd=%s",
                apiKey, movie.getMovieCd()
        );

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode movieInfoResult = root.path("movieInfoResult");
            JsonNode movieInfo = movieInfoResult.path("movieInfo");

            String genreNm = movieInfo.withArray("genres").get(0).get("genreNm").asText();
            String showTm = movieInfo.get("showTm").asText();
            String watchGradeNm = movieInfo.withArray("audits").get(0).get("watchGradeNm").asText();
            String nations = movieInfo.withArray("nations").get(0).get("nationNm").asText();
            StringBuilder actors = new StringBuilder();
            for (JsonNode actor : movieInfo.withArray("actors")) {
                actors.append(actor.get("peopleNm").asText());
                actors.append(", ");
            }
            if (actors.length() > 0) {
                actors.setLength(actors.length() - 2);
            }

            movie.setGenreNm(genreNm);
            movie.setShowTm(showTm);
            movie.setWatchGradeNm(watchGradeNm);
            movie.setNations(nations);
            movie.setActors(actors.toString());

            movieRepository.save(movie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
