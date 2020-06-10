package tr.salkan.code.java.web.async.restservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskExample {

    @JsonProperty("isProcessed")
    private Boolean isProcessed;
    @JsonProperty("error")
    private String error;
    @JsonProperty("processName")
    private String processName;
    @JsonProperty("entityProcessResult")
    private String entityProcessResult;


    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getEntityProcessResult() {
        return entityProcessResult;
    }

    public void setEntityProcessResult(String entityProcessResult) {
        this.entityProcessResult = entityProcessResult;
    }
}
