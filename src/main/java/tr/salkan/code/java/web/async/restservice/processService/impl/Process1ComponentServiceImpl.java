package tr.salkan.code.java.web.async.restservice.processService.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.dto.response.ResponseObject;
import tr.salkan.code.java.web.async.restservice.processService.ProcessComponentService;

import java.util.List;

@Service
@Qualifier("modul1")
public class Process1ComponentServiceImpl implements ProcessComponentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Process1ComponentServiceImpl.class);

    @Override
    public ResponseObject makeProcess(List<Person> personList, ProcessModulEnum modul) {


        ResponseObject responseObject = new ResponseObject();

        personList.forEach(person -> {

            //System.out.println("process 1 - person number " + person.getNumber());

            if(person.getNumber()%3 == 0)
            {
                person.setProcessEd(false);
                person.setProcessError("ERROR_" + person.getNumber());
            }
            else
            {
                person.setProcessEd(true);
                person.setProcessError("");
            }

        });

        responseObject.setPersonList(personList);
        responseObject.setModul(modul);
//
        return responseObject;

    }
}
