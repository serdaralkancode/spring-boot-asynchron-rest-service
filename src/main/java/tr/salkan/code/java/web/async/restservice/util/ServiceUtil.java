package tr.salkan.code.java.web.async.restservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServiceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);

    public static String getMd5Hashing(String name)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        md.update(name.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
