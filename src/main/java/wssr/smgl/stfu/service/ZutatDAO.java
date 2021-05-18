package wssr.smgl.stfu.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wssr.smgl.stfu.repository.InhaltRepository;
import wssr.smgl.stfu.repository.ZutatRepository;
import wssr.smgl.stfu.model.Zutat;

@Service
@Transactional
public class ZutatDAO {
    @Autowired
    private ZutatRepository zutatRepository;
    @Autowired
    private InhaltRepository inhaltRepository;

    public Iterable<Zutat> getAllZutaten(){
        return zutatRepository.findAll();
    }

    public void create(String  name) {
        Zutat zutat = new Zutat(name);
        zutatRepository.save(zutat);
    }

    public Zutat findById(Long id) {
        return zutatRepository.findById(id).get();
    }

    public Zutat findByName(String name) {
        Iterable<Zutat> zutat = zutatRepository.findByName(name);
        Iterator<Zutat> iterator = zutat.iterator();
        return iterator.next();
    }

    public Iterable<Zutat> findAll(){return zutatRepository.findAll();}

    public Zutat save(Zutat zutat) {
        zutatRepository.save(zutat);
        return zutat;
    }

    public void delete(Long id) throws DaoException {
        Zutat zutat = zutatRepository.findById(id).get();
        zutatRepository.delete(zutat);
    }

}
