package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
