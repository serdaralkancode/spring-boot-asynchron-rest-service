package tr.salkan.code.java.web.async.restservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;

import java.util.List;

public class ResponseObject {

    @JsonProperty("modul")
    private ProcessModulEnum modul;
    @JsonProperty("personList")
    private List<Person> personList;

    public ProcessModulEnum getModul() {
        return modul;
    }

    public void setModul(ProcessModulEnum modul) {
        this.modul = modul;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
