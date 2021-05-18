package wssr.smgl.stfu.repository;

import wssr.smgl.stfu.model.Zutat;
import org.springframework.data.repository.CrudRepository;

public interface ZutatRepository extends CrudRepository<Zutat, Long> {
    Iterable<Zutat> findByName(String name);

}
