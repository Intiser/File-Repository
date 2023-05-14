package com.ahsan.file.app.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Component
public class Utils {

    public final Random random = new SecureRandom();
    private final String ALPHABET = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;

    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public static long getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

    public static String checksum(byte[] bytes) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // file hashing with DigestInputStream
//        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
//            while (dis.read() != -1) ; //empty loop to clear the data
//            md = dis.getMessageDigest();
//        }
        for (byte byt : bytes) {
            md.update(byt);
        }


        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }

    public static String getUniqueDownnloadLink(String s) throws IOException, NoSuchAlgorithmException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = bytesToHex(salt.digest());
        return s + digest;
    }

    public static String getUniquesId() throws IOException, NoSuchAlgorithmException{
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = bytesToHex(salt.digest());
        return digest;
    }

    public static String getDownnloadLink(String s)  {
        return "http://localhost:4200/download/" + s;
    }


    private static String bytesToHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02X", b));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String getDownLink(String UUID) {
        return "http://localhost:4200/download/" + UUID;
    }


    public static LocalDateTime getCurrentDat() {
        return LocalDateTime.now();
    }

//    public static Date getCurrentDat(){
//        LocalDateTime localDateTime = LocalDateTime.now();
//        LocalDate localDate = localDateTime.toLocalDate();
//        return asDate(localDate);
//    }


    private static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


}
