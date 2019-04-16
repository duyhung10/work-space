package demo.repository;

import org.springframework.data.repository.CrudRepository;

import demo.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
