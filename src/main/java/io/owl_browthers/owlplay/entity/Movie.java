package io.owl_browthers.owlplay.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String movieCd;

    @Column(nullable = false)
    private String movieNm;

    @Column(nullable = false)
    private LocalDate openDt;

    @Column(nullable = false)
    private String directors;

    private String actors;
    private String genreNm;
    private String watchGradeNm;
    private String showTm;
    private String nations;
}
