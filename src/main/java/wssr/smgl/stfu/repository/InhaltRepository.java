package wssr.smgl.stfu.repository;

import org.springframework.data.repository.CrudRepository;
import wssr.smgl.stfu.model.Inhalt;

public interface InhaltRepository extends CrudRepository<Inhalt, Long> {
    //@Query(value = "SELECT rezept_name, tbl_zutat.name
    // FROM tbl_rezept, tbl_rezeptinhalt
    //INNER JOIN tbl_zutat ON tbl_rezeptinhalt.rezept_id = tbl_rezeptinhalt.zutat_id
    //WHERE tbl_zutat.name = tbl_vorratskammer.zutatname AND tbl_zutat.mengeingramm = tbl_vorratskammer.mengeingramm")
    //List<Rezept> findRezeptverfuegbar(User user);
}
