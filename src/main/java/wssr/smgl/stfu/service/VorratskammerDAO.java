package wssr.smgl.stfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wssr.smgl.stfu.model.Vorratskammer;
import wssr.smgl.stfu.repository.VorratskammerRepository;
import wssr.smgl.stfu.repository.UserRepository;

@Service
public class VorratskammerDAO {

    @Autowired
    private UserRepository userrepository;
    @Autowired
    private VorratskammerRepository vorratskammerRepository;

    public void create(String zutatname, int mengeingramm){
        Vorratskammer vorratskammer = new Vorratskammer(zutatname,mengeingramm);
        vorratskammerRepository.save(vorratskammer);
    }

    public Vorratskammer findById(Long id) {
        return vorratskammerRepository.findById(id).get();
    }

    public String findByName(String name) {
        return name;
    }

    public Iterable<Vorratskammer> findAll() {
        return vorratskammerRepository.findAll();
    }

    public Vorratskammer save(Vorratskammer zutat) {
        vorratskammerRepository.save(zutat);
        return zutat;
    }

    public void delete(Long id) throws DaoException {
        Vorratskammer vorratskammer = vorratskammerRepository.findById(id).get();
        vorratskammerRepository.delete(vorratskammer);
    }

}
