package io.owl_browthers.owlplay.repository;
import io.owl_browthers.owlplay.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}