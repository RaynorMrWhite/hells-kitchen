package wssr.smgl.stfu.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;
import wssr.smgl.stfu.model.User;

@Service
public class UserDAO {
    private static Logger log = LogManager.getLogger(UserDAO.class);

    public void createUser(String name, String password){
        User u = new User();
        u.setName(name);
        u.setPassword(password);
    }


}
