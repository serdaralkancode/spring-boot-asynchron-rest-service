package tr.salkan.code.java.web.async.restservice.processComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.dto.request.RequestObject;
import tr.salkan.code.java.web.async.restservice.dto.response.ResponseObject;
import tr.salkan.code.java.web.async.restservice.processService.ProcessComponentService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ProcessModul2Component  {

    private final ProcessComponentService processComponentService;

    @Autowired
    public ProcessModul2Component(@Qualifier("modul2") ProcessComponentService processComponentService) {
        this.processComponentService = processComponentService;
    }

    @Async("serviceAsyncExecutor")
    public CompletableFuture<ResponseObject> makeProcess2(RequestObject requestObject) {


        List<Person> personsList1 = requestObject.getPersonList().stream().filter(person -> person.getModul().equals(ProcessModulEnum.MODUL2)).collect(Collectors.toList());

        ResponseObject responseObject = processComponentService.makeProcess(personsList1,ProcessModulEnum.MODUL2);

        return CompletableFuture.completedFuture(responseObject);
    }
}
