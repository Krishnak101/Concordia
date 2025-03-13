package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSADecryption {


    public static void decryptMessage(List<BigInteger> encryptedMsg) {
        StringBuilder decryptedMessage = new StringBuilder();
        List<String> decryptedChunks = new ArrayList<>();

        for (BigInteger integer : encryptedMsg) {
            // Decrypt the encrypted BigInteger using the private exponent and modulus
            BigInteger decryptedValue = SquareAndMultiply.squareAndMultiply(integer, RSAConstants.my_d, RSAConstants.my_N);
            
            // Convert the decrypted value to a fixed-length hexadecimal string
            String hexString = String.format("%04x", decryptedValue);
            
            // Convert hexadecimal to a byte array
            byte[] byteArray = hexToBytArray(hexString);
            
            // Convert the byte array to string and then adding it to the decrypted chunks
            String decString = new String(byteArray);
            decryptedChunks.add(decString);
            
            // Append the decrypted string to the final decrypted message
            decryptedMessage.append(decString);
        }

        // Print the decrypted chunks and the final decrypted message
        System.out.println("Chunks : " + decryptedChunks);
        System.out.println("Decrypted msg is : " + decryptedMessage);
    }

    /**
     *  Utility method to convert a hexadecimal string to a byte array
     * @param str_hex
     * @return
     */
    private static byte[] hexToBytArray(String str_hex) {
        int size = str_hex.length();
        byte[] Arr = new byte[size / 2];

        for (int b = 0; b < size; b += 2) {
            Arr[b / 2] = (byte) ((Character.digit(str_hex.charAt(b), 16) << 4)
                    + Character.digit(str_hex.charAt(b + 1), 16));
        }

        return Arr;
    }


    public static void main(String[] args) {

        List<BigInteger> encryptedMsg = List.of(
                new BigInteger("2067682998"),
                new BigInteger("1706259979"),
                new BigInteger("843750782"),
                new BigInteger("256625186"),
                new BigInteger("2700677192"),
                new BigInteger("1769577863"),
                new BigInteger("1747960636"),
                new BigInteger("2386973219"));


        RSADecryption.decryptMessage(encryptedMsg);
    }
}
