package tr.salkan.code.java.web.async.restservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;

import java.io.Serializable;

public class Person implements Serializable {

    @JsonProperty("personId")
    private Integer number;

    @JsonIgnore
    private String personUUId;

    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private Boolean isProcessEd;

    @JsonIgnore
    private String processError;

    @JsonProperty("modul")
    private ProcessModulEnum modul;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPersonUUId() {
        return personUUId;
    }

    public void setPersonUUId(String personUUId) {
        this.personUUId = personUUId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProcessEd() {
        return isProcessEd;
    }

    public void setProcessEd(Boolean processEd) {
        isProcessEd = processEd;
    }

    public String getProcessError() {
        return processError;
    }

    public void setProcessError(String processError) {
        this.processError = processError;
    }

    public ProcessModulEnum getModul() {
        return modul;
    }

    public void setModul(ProcessModulEnum modul) {
        this.modul = modul;
    }

    @Override
    public String toString() {
        return "Person{" +
                "number=" + number +
                ", personUUId='" + personUUId + '\'' +
                ", name='" + name + '\'' +
                ", isProcessEd=" + isProcessEd +
                ", processError='" + processError + '\'' +
                ", modul=" + modul +
                '}';
    }
}
