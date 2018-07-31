package team.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    public MD5Generator() {
    }

    public String getMD5(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(s.getBytes());
        digest = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, digest);
        String md5Hex = bigInteger.toString(16);
        while(md5Hex.length() < 32){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
