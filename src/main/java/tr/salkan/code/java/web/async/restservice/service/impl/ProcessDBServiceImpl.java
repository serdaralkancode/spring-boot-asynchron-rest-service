package tr.salkan.code.java.web.async.restservice.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.repo.ProcessRepo;

import java.util.List;

@Service
public class ProcessDBServiceImpl {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProcessDBServiceImpl.class);


    private final ProcessRepo processRepo;

    @Autowired
    public ProcessDBServiceImpl(ProcessRepo processRepo) {
        this.processRepo = processRepo;
    }

    public void saveDatabase(List<Person> personList) {

        System.out.println("***********************************************");

        for(Person p : personList)
        {
            processRepo.savePerson(p);
        }

        // list to send repo service
    }
}
