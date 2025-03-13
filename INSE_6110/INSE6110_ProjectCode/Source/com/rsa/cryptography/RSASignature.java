package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSASignature {
   
	/**
	 * Method used to perform the signature
	 */
    public static void sign() {
        RSAEncryption rsaEncryption = new RSAEncryption();
 
        List<Integer> messagerAsIntegerArray = rsaEncryption.divideMsg(RSAConstants.mySignature);
        List<BigInteger> encMsg = new ArrayList<>();
        for (int integer : messagerAsIntegerArray) {
            encMsg.add(SquareAndMultiply.squareAndMultiply(BigInteger.valueOf(integer), RSAConstants.my_d, RSAConstants.my_N));
        }
        System.out.println("Signature is: " + encMsg);
    }

    /**
     *  Method used to verify the signature
     */
    public static void verify() {
        StringBuilder decryptedMessage = new StringBuilder();
        for (BigInteger integer : RSAConstants.partnersSignature) {
            BigInteger decryptedValue = SquareAndMultiply.squareAndMultiply(integer, RSAConstants.e, RSAConstants.N); // partner's e and N
            String hexString = String.format("%04x", decryptedValue);
            byte[] byteArray = hexStringToByteArray(hexString);
            String decString = new String(byteArray);
            decryptedMessage.append(decString);
        }
        System.out.println("Verified Signature is: " + decryptedMessage);
    }

    /**
     * Method used to convert the HexaDecimal String to Byte Array
     * @param hexString
     * @return
     */
    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len - 1; i += 2) {
            char firstChar = hexString.charAt(i);
            char secondChar = hexString.charAt(i + 1);
            int firstDigit = Character.digit(firstChar, 16);
            int secondDigit = Character.digit(secondChar, 16);
            if (firstDigit != -1 && secondDigit != -1) {
                data[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
            } else {
                data[i / 2] = 0;
            }
        }
        return data;
    }

    public static void main(String[] args) {
        RSASignature.sign(); 
        RSASignature.verify(); 
    }
}
