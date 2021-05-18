package wssr.smgl.stfu.repository;

import org.springframework.data.repository.CrudRepository;
import wssr.smgl.stfu.model.User;

public interface UserRepository extends CrudRepository <User , Long>{
    Iterable<User>  findByUserName(String name);
}
