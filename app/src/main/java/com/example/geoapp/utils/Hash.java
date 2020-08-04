package com.example.geoapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private static final String SHA_1 = "SHA-1";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_512 = "SHA-512";

    public static String getSha1( String data ) throws NoSuchAlgorithmException {
        return Hash.getHash( data , SHA_1);
    }

    public static String getSha256( String data ) throws NoSuchAlgorithmException {
        return Hash.getHash( data , SHA_256);
    }

    public static String getSha512( String data ) throws NoSuchAlgorithmException {
        return Hash.getHash( data , SHA_512);
    }

    public static String getHash( String data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance(algorithm);
        byte[] bytes = sha1.digest( data.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02X",b));
        }
        return builder.toString().toLowerCase();
    }
}
