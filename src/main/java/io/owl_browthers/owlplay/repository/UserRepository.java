package io.owl_browthers.owlplay.repository;
import io.owl_browthers.owlplay.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
}