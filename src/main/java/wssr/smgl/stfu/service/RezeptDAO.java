package wssr.smgl.stfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wssr.smgl.stfu.model.Rezept;
import wssr.smgl.stfu.model.User;
import wssr.smgl.stfu.repository.InhaltRepository;
import wssr.smgl.stfu.repository.RezeptRepository;
import wssr.smgl.stfu.repository.ZutatRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class RezeptDAO {
    @Autowired
    private InhaltDAO inhaltDAO;
    @Autowired
    private RezeptRepository rezeptRepository;
    @Autowired
    private InhaltRepository inhaltRepository;
    @Autowired
    private ZutatRepository zutatRepository;

    public Iterable<Rezept> getAllrezepte(){
        return rezeptRepository.findAll();
    }

    public void create() {
        Rezept rezept = new Rezept();
        rezeptRepository.save(rezept);
    }

    public Rezept findById(Long id) {
        return rezeptRepository.findById(id).get();
    }

    public Rezept findByName(String rezeptName) {
        Iterable<Rezept> rezepts = rezeptRepository.findByRezeptName(rezeptName);
        Iterator<Rezept> iterator = rezepts.iterator();
        return iterator.next();
    }

    public Rezept save(Rezept rezept) {
        rezeptRepository.save(rezept);
        return rezept;
    }


   //public List<Rezept> findVerfuegbare(User user) {
    //   return rezeptRepository.findRezeptverfuegbar(user);
   //}

    public void delete(Long id) throws DaoException {
        Rezept rezept = rezeptRepository.findById(id).get();
        rezeptRepository.delete(rezept);
    }

}
