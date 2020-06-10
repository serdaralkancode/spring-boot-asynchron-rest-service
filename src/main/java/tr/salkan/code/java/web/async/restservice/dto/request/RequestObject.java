package tr.salkan.code.java.web.async.restservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tr.salkan.code.java.web.async.restservice.deserializer.CustomGenericDeSerializer;
import tr.salkan.code.java.web.async.restservice.deserializer.LocalDateTimeDeserializer;
import tr.salkan.code.java.web.async.restservice.entity.EntityExampleObject;
import tr.salkan.code.java.web.async.restservice.serializer.CustomGenericSerializer;
import tr.salkan.code.java.web.async.restservice.serializer.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class RequestObject {

    @JsonProperty("codeType")
    private String codeType;

    @JsonProperty("personList")
    private List<Person> personList;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime processStartTime;

    @JsonSerialize(using = CustomGenericSerializer.class)
    @JsonDeserialize(using = CustomGenericDeSerializer.class)
    private EntityExampleObject entityExampleObject;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public LocalDateTime getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(LocalDateTime processStartTime) {
        this.processStartTime = processStartTime;
    }

    public EntityExampleObject getEntityExampleObject() {
        return entityExampleObject;
    }

    public void setEntityExampleObject(EntityExampleObject entityExampleObject) {
        this.entityExampleObject = entityExampleObject;
    }
}