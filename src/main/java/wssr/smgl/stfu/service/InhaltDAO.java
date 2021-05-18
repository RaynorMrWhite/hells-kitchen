package wssr.smgl.stfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wssr.smgl.stfu.model.Inhalt;
import wssr.smgl.stfu.model.Rezept;
import wssr.smgl.stfu.model.Zutat;
import wssr.smgl.stfu.repository.InhaltRepository;
import wssr.smgl.stfu.repository.RezeptRepository;
import wssr.smgl.stfu.repository.ZutatRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional
public class InhaltDAO {
    @Autowired
    private InhaltRepository inhaltRepository;
    @Autowired
    private ZutatRepository zutatRepository;
    @Autowired
    private RezeptRepository rezeptRepository;

    public void erstelle(Long inhaltId, Long[] anzahlZutaten) throws  DaoException{
        Rezept r = rezeptRepository.findById(inhaltId).get();
        Set<Zutat> zutatliste = new HashSet<Zutat>();
        for(Long zutatenId : anzahlZutaten) {
            zutatliste.add(zutatRepository.findById(zutatenId).get());
        }
        Inhalt i = new Inhalt();
        i.setRezept(r);
        i.setZutat(zutatliste);
        try {
            inhaltRepository.save(i);
        } catch (DataAccessException e) {
            throw new DaoException("Die selbe Zutat kann nicht zweimal in einem Rezept enthalten sein, ändere die Menge.");
        }
    }

    public Iterable<Inhalt> findAll() {
        return inhaltRepository.findAll();

    }

}
