package io.owl_browthers.owlplay.dto;
import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class SignUpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String password2;
    private String name;
    private String contact;
    private String birthday;
}