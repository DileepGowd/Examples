package com.controller;
 

import org.jasypt.util.text.BasicTextEncryptor;
 
/**
 *
 * @author MM <mushfiqazeri@gmail.com>
 */
public class TestJasypt {
    
    private static char[] password= {'s','u','k','a','n','y','a'};
 
  
 
    public static void main(String[] args) {
        String text = "This is my private data";
        String encrypt = encrypt(text);
        String decrypt = decrypt(encrypt);
        System.out.println("\noriginal: " + text);
        System.out.println("\nencyrpt:  " + encrypt);
        System.out.println("\ndecyrpt:  " + decrypt);
    }
 
    private static String encrypt(String text) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(password);
        String encryptedText = textEncryptor.encrypt(text);
 
        return encryptedText;
    }
 
    private static String decrypt(String text) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(password);
        String decryptedText = textEncryptor.decrypt(text);
 
        return decryptedText;
    }
    
    public static char[] getPassword() {
        return password;
    }
    
}