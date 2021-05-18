package wssr.smgl.stfu.repository;

import org.springframework.data.jpa.repository.Query;
import wssr.smgl.stfu.model.Rezept;
import org.springframework.data.repository.CrudRepository;
import wssr.smgl.stfu.model.User;

import java.util.List;


public interface RezeptRepository extends CrudRepository<Rezept, Long>{
    Iterable<Rezept> findByRezeptName(String rezeptname);
}
