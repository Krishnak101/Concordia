package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class SquareAndMultiply {

//	 // Method to calculate the modular multiplicative inverse of a number
//    public static BigInteger inverseModulo(BigInteger base, BigInteger mod) {
//        BigInteger prevRes = BigInteger.ZERO;
//        BigInteger res = BigInteger.ONE;
//        BigInteger reminder = mod;
//        BigInteger prevReminder = base;
//
//        // Extended Euclidean algorithm
//        while (!prevReminder.equals(BigInteger.ONE)) {
//            BigInteger curPrevRem = prevReminder;
//            prevReminder = reminder.mod(prevReminder);
//            BigInteger curPrevRes = res;
//            res = prevRes.subtract(res.multiply(reminder.divide(curPrevRem)));
//            prevRes = curPrevRes;
//            reminder = curPrevRem;
//        }
//
//        return res.mod(mod);
//    }
    
    public static BigInteger inverseMod(BigInteger base, BigInteger mod) {
    	BigInteger previousResult = BigInteger.ZERO;
        BigInteger result = BigInteger.ONE;
        BigInteger remainder = mod;
        BigInteger currentRemainder = base;
        
        // Extended Euclidean Algorithm
        while(!currentRemainder.equals(BigInteger.ONE)) {
        	BigInteger currentPreviousRemainder = currentRemainder;
        	currentRemainder = remainder.mod(currentRemainder);
        	BigInteger cuurentPreviousResult = result;
        	result = previousResult.subtract(result.multiply(remainder.divide(currentPreviousRemainder)));
        	previousResult = cuurentPreviousResult;
        	remainder = currentPreviousRemainder;
        }
        
        return result.mod(mod);
    }

    // Method to perform modular exponentiation using square-and-multiply algorithm
    public static BigInteger squareAndMultiply(BigInteger integer, BigInteger exponent, BigInteger mod) {
        String exponentInBinary = exponent.toString(2);
        List<BigInteger> squares = new ArrayList<>();
        squares.add(integer);
        BigInteger powers = new BigInteger("2");

        while (powers.compareTo(exponent) <= 0 && powers.compareTo(BigInteger.ZERO) > 0 && exponent.compareTo(BigInteger.ZERO) > 0) {
            squares.add(squares.get(squares.size() - 1).pow(2).mod(mod));
            powers = powers.multiply(new BigInteger("2"));
        }

        BigInteger result = BigInteger.ONE;
        int inc = 0;

        for (int i = exponentInBinary.length() - 1; i >= 0; i--) {
            if (exponentInBinary.charAt(i) == '1') {
                result = result.multiply(squares.get(inc)).mod(mod);
            }
            inc++;
        }

        return result;
    }

    // Example usage in the main method
    public static void main(String[] args) {
        // Example usage:
    	int base = 5;
        BigInteger exp = new BigInteger("20");
        BigInteger mod = new BigInteger("504");

        // Calculate modular exponentiation using square-and-multiply
        BigInteger result = SquareAndMultiply.squareAndMultiply(BigInteger.valueOf(base), exp, mod);
        System.out.println("Result: " + result);

        // Calculate modular multiplicative inverse
        BigInteger inverseResult = SquareAndMultiply.inverseMod(BigInteger.valueOf(base), mod);
        System.out.println("Inverse Modulo Result: " + inverseResult);
    }
}
