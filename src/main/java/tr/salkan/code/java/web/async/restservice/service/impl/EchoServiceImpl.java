package tr.salkan.code.java.web.async.restservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.dto.request.RequestObject;
import tr.salkan.code.java.web.async.restservice.entity.EntityExampleObject;
import tr.salkan.code.java.web.async.restservice.service.EchoService;
import tr.salkan.code.java.web.async.restservice.util.ServiceUtil;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class EchoServiceImpl implements EchoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceImpl.class);

    /* using generate request json */

    @Override
    public void createRequestObject() {

        try
        {
            ObjectMapper mapper = new ObjectMapper();
            RequestObject requestObject = new RequestObject();

            requestObject.setProcessStartTime(LocalDateTime.now());
            requestObject.setCodeType("info");

            EntityExampleObject entityExampleObject = new EntityExampleObject();
            entityExampleObject.setId(1000l);
            entityExampleObject.setName("entityExampleObject1");
            entityExampleObject.setField1("entityExampleObject1 field1");
            entityExampleObject.setField2("entityExampleObject1 field2");

            requestObject.setEntityExampleObject(entityExampleObject);

            Stream<Person> streamPerson = IntStream.range(1, 200).mapToObj(
                    i -> {

                      Person person = createPerson(i);

                      return person;
                    });

            List<Person> listPerson = streamPerson.collect(Collectors.toList());

            requestObject.setPersonList(listPerson);

            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(requestObject);

            System.out.println("request string : " + jsonInString);
        }
        catch (JsonProcessingException e)
        {
            LOGGER.error("EXCEPTİON : " + e.getMessage());
        }
    }

    private Person createPerson(int i) {

        Person person = new Person();
        Integer gId = i + (i*2);
        person.setName(ServiceUtil.getMd5Hashing("A_B_" +gId));
        person.setNumber(i);
        person.setPersonUUId(generateUUID(UUID.randomUUID()));
        person.setProcessEd(false);

        if(i < 40)
        {
            person.setModul(ProcessModulEnum.MODUL2);
        }
        else if (i >= 40 && i <= 100 )
        {
            person.setModul(ProcessModulEnum.MODUL3);
        }
        else
        {
            person.setModul(ProcessModulEnum.MODUL1);
        }

        return person;

    }

    public String generateUUID(UUID id) {
        long hi = id.getMostSignificantBits();
        long lo = id.getLeastSignificantBits();
        byte[] bytes = ByteBuffer.allocate(16).putLong(hi).putLong(lo).array();
        BigInteger big = new BigInteger(bytes);
        String numericUuid = big.toString().replace('-', '1'); // just in case
        return numericUuid;
    }
}
