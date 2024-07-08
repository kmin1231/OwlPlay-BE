package io.owl_browthers.owlplay.entity;
import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // automatically

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String password2;     // password confirmation

    @Column(nullable = false)
    private String name;

    @Column(length = 15)
    private String contact;

    @Column(length = 8)
    private String birthday;
}