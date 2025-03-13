package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.Random;

public class RSAUtility {

	public static BigInteger Big16BitPrimeGenerator() {
		Random random = new Random();
		BigInteger randomPrime;
		
		do {
			randomPrime = generateRandom16BitPrime(random);
		} while (randomPrime == null);
		
		System.out.println("16-bit Random Prime Number is : " + randomPrime);
		return randomPrime;
	}
	
	public static BigInteger generateRandom16BitPrime(Random random) {
		int bitLength = 16; // 16 bits
		BigInteger minLimit = BigInteger.ONE.shiftLeft(bitLength - 1); // minLimit = 2^15
		BigInteger maxLimit = BigInteger.ONE.shiftLeft(bitLength).subtract(BigInteger.ONE);
		
		BigInteger randomNum;
		
		do {
			// Generate random 16-bit number within the specified range
			randomNum = new BigInteger(bitLength, random);
			
			// Making sure that, it's within the specified range
			if (randomNum.compareTo(minLimit) < 0) {
				randomNum = randomNum.add(minLimit);
			}
			
			// Make sure it's odd to increase the chances of finding a prime
			randomNum = randomNum.setBit(0);
			
			// Find the next prime number starting from the generated number
			randomNum = randomNum.nextProbablePrime();
		} while (randomNum.compareTo(maxLimit) >= 0);
		
		return randomNum;
	}
	
	public static BigInteger GCD_e_Phi_n(BigInteger Phi_N, BigInteger e) {
		return e.gcd(Phi_N);
	}
	
    public static void main(String[] args) {

        BigInteger p = Big16BitPrimeGenerator();
        BigInteger q = Big16BitPrimeGenerator();
        System.out.println("p:" + p);
        System.out.println("q:" + q);

        BigInteger N = p.multiply(q);
        System.out.println("N:" + N);

        BigInteger Phi_N = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Phi_N:" + Phi_N);

        BigInteger e = Big16BitPrimeGenerator();
        System.out.println("e:" + e);

        BigInteger gcd = GCD_e_Phi_n(Phi_N, e);
        System.out.println("GCD of e and Phi_N is: " + gcd);
    }

}
