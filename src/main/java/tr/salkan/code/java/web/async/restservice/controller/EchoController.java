package tr.salkan.code.java.web.async.restservice.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tr.salkan.code.java.web.async.restservice.locale.MessageService;
import tr.salkan.code.java.web.async.restservice.service.EchoService;

@Controller
public class EchoController {


    private static final Logger LOGGER = LoggerFactory.getLogger(EchoController.class);

    private final EchoService echoService;

    private final @Getter MessageService messageService;

    @Autowired
    public EchoController(EchoService echoService, MessageService messageService) {
        this.echoService = echoService;
        this.messageService = messageService;
    }

    @RequestMapping(value = "/EchoController/echo", method = RequestMethod.GET)
    @ResponseBody
    public String echo() {

        echoService.createRequestObject();
        return messageService.getMessage("service.echoMesaj");
    }

}
