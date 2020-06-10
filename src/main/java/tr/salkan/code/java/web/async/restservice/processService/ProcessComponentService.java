package tr.salkan.code.java.web.async.restservice.processService;

import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.dto.response.ResponseObject;

import java.util.List;

public interface ProcessComponentService {

      ResponseObject makeProcess(List<Person> personList, ProcessModulEnum modul1);

}
