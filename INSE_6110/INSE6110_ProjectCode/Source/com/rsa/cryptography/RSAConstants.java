package com.rsa.cryptography;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;


public class RSAConstants {
	// my own p and q values
	public static final BigInteger p = new BigInteger("51413");
	public static final BigInteger q = new BigInteger("58427");
	public static final BigInteger my_N = p.multiply(q);
	
	// phiOfN = (p-1) * (q-1)
	public static final BigInteger my_phiOfN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    public static final BigInteger my_e = new BigInteger("4097");
//	public static final BigInteger my_e = new BigInteger("58943");
    public static final BigInteger my_d = SquareAndMultiply.inverseMod(my_e, my_phiOfN);

    // Partner's N and e values for Encrypting my message
    public static final BigInteger N = new BigInteger("3583881787");
    public static final BigInteger e = new BigInteger("58943");
    
    // Messages for communication
    public static final String msgToBeEncrypted = "Bonjour je suis Krishna";
    //public static final List<Integer> msgToBeDecrypted = Arrays.asList(
     //       987881663, 1658138511, 1946382762, 114993941, 2067727052, 280669318, 1204057221, 2101752276);
    
    // Signatures
    public static final String mySignature = "Krishna Kunati";
//    public static final String mySignature = "Vaishnavi Kalathur";
//    public static final List<BigInteger> partnersSignature = List.of(
//            new BigInteger("1863475167"),
//            new BigInteger("759025518"),
//            new BigInteger("2101775696"),
//            new BigInteger("2490687276"));
    public static final List<BigInteger> partnersSignature = Arrays.asList(
            new BigInteger("3422436572"),
            new BigInteger("1211448874"),
            new BigInteger("2139420042"),
            new BigInteger("1013847431"),
            new BigInteger("316437771"),
            new BigInteger("512996235"));
    public static void main(String[] args) {
    	System.out.println("My p is : "+p);
    	System.out.println("My q is : "+q);
    	System.out.println("My N is : "+my_N);
    	System.out.println("My e is : "+my_e);
    	System.out.println("My Phi(N) is : "+my_phiOfN);

        BigInteger gcd = RSAUtility.GCD_e_Phi_n(my_phiOfN, my_e);
        System.out.println("GCD of e and Phi_N is: " + gcd);
    	System.out.println("My d is : "+my_d);
    }
    	
}
