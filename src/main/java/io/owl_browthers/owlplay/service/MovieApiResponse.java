package io.owl_browthers.owlplay.service;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieApiResponse {
    @JsonProperty("movieInfoResult")
    private MovieInfoResult movieInfoResult;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfoResult {
        @JsonProperty("movieInfo")
        private MovieInfo movieInfo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfo {
        @JsonProperty("movieCd")
        private String movieCd;

        @JsonProperty("movieNm")
        private String movieNm;

        @JsonProperty("openDt")
        private String openDt;

        @JsonProperty("directors")
        private List<Director> directors;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Director {
        @JsonProperty("peopleNm")
        private String peopleNm;
    }
}
