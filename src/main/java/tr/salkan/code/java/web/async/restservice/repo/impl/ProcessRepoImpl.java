package tr.salkan.code.java.web.async.restservice.repo.impl;

import org.springframework.stereotype.Repository;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.repo.ProcessRepo;

@Repository
//@Transactional  -> add hibernate dependency to maven
public class ProcessRepoImpl implements ProcessRepo {


    @Override
    public void savePerson(Person person) {

        System.out.println("Person : " + person.toString());

        //save process to db;
    }
}
