package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSAEncryption {

    /**
     *  Method to divide the input message into chunks of three characters and convert them to integers
     */
    public List<Integer> divideMsg(String message) {
        List<Integer> msgInInt = new ArrayList<>();
        int count=1;
        for (int i = 0; i < message.length(); i += 3) {
            // Extract a chunk of three characters or less from the message
            String chunk = message.substring(i, Math.min(i + 3, message.length()));
            System.out.print("Chunk ("+(count)+ ") : " + chunk +"  ::  ");
            // Convert the chunk to an integer
            msgInInt.add(convertStringToInt(chunk));
            count++;
        }
        System.out.println(msgInInt);
        return msgInInt;
    }

    /**
     *  Method to convert a three-character chunk to an integer
     * @param chunk
     * @return integer
     */
    private int convertStringToInt(String chunk) {
        byte[] bytes = chunk.getBytes();
        StringBuilder hex = new StringBuilder();
        // Convert the chunk's characters to their hexadecimal representation
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        } 
        System.out.println(hex);
        // Parse the hexadecimal string to an integer
        return Integer.parseInt(hex.toString(), 16);
    }

    /**
     *  Method to encrypt the message using RSA
     * @param message
     * @param e
     * @param N
     */
    public void encryptMsg(String message, BigInteger e, BigInteger N) {
        // Divide the message into integers
        List<Integer> msgInInt = divideMsg(message);
        List<BigInteger> encMsg = new ArrayList<>();
        // Encrypt each integer using modular exponentiation
        for (int integer : msgInInt) {
            encMsg.add(SquareAndMultiply.squareAndMultiply(BigInteger.valueOf(integer), e, N));
        }
        System.out.println("Encrypted msg is : " + encMsg);
    }


    public static void main(String[] args) {
		
    	RSAEncryption rsaEnc = new RSAEncryption();
    	rsaEnc.encryptMsg(RSAConstants.msgToBeEncrypted, RSAConstants.e,RSAConstants.N);
    }
}
