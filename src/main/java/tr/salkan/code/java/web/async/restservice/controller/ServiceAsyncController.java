package tr.salkan.code.java.web.async.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.salkan.code.java.web.async.restservice.dto.request.RequestObject;
import tr.salkan.code.java.web.async.restservice.errorhandling.ServiceCustomException;
import tr.salkan.code.java.web.async.restservice.service.ProcessAsyncMainService;

import java.io.Serializable;

@Controller
public class ServiceAsyncController implements Serializable {

    private final ProcessAsyncMainService processAsyncMainService;

    @Autowired
    public ServiceAsyncController(ProcessAsyncMainService processAsyncMainService) {
        this.processAsyncMainService = processAsyncMainService;
    }

    @PostMapping("/ProcessService/processWorks")
    public ResponseEntity<Boolean> processWorks(@RequestBody RequestObject requestObject) throws ServiceCustomException {

        if(requestObject.getPersonList().isEmpty())
        {
            throw new ServiceCustomException("ERROR : PERSON LIST IS EMPTY");
        }

        processAsyncMainService.processAllWorks(requestObject);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
