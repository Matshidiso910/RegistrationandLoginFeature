/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;

import java.util.Scanner;

/**
 * Project: Registration and Login Feature
 * Part: 1 of POE (Portfolio of Evidence)
 * 
 * @author Student
 * @version 1.0
 * @date 2024
 * 
 * REFERENCES AND ATTRIBUTIONS:
 * 
 * REFERENCE 1: Phone Number Validation Regex
 * Source: Stack Overflow
 * URL: https://stackoverflow.com/questions/123559/how-to-validate-phone-numbers-using-regex
 * Date Accessed: 2024
 * Accuracy: Community-verified with 900+ upvotes
 * 
 * REFERENCE 2: Password Security Guidelines
 * Source: OWASP (Open Web Application Security Project)
 * URL: https://owasp.org/www-community/password-special-characters
 * Date Accessed: 2024
 * Accuracy: Industry standard for security
 * 
 * REFERENCE 3: South African Phone Number Format
 * Source: ITU-T E.164 Recommendation
 * URL: https://www.itu.int/rec/T-REC-E.164-201011-I/
 * Date Accessed: 2024
 * Accuracy: Official UN standard
 * 
 * REFERENCE 4: Java Scanner Class
 * Source: Oracle Java Documentation
 * URL: https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
 * Date Accessed: 2024
 * Accuracy: Official documentation
 * 
 * REFERENCE 5: Java Pattern and Matcher Classes
 * Source: Oracle Java Documentation
 * URL: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * Date Accessed: 2024
 * Accuracy: Official documentation
 */
public class RegistrationAndLoginFeature {

    private Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        RegistrationAndLoginFeature system = new RegistrationAndLoginFeature();
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("WELCOME TO THE REGISTRATION AND LOGIN FEATURE");
        System.out.println("Part 1 of POE");
        System.out.println("==================================================");
        
        System.out.print("\nEnter your first name: ");
        String firstName = system.input.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = system.input.nextLine();
        
        System.out.print("Enter a username (must contain '_' and be <= 5 characters): ");
        String username = system.input.nextLine();
        
        System.out.print("Enter a password (min 8 chars, 1 capital, 1 number, 1 special char): ");
        String password = system.input.nextLine();
        
        System.out.print("Enter your South African cell phone number (format: +27XXXXXXXXX): ");
        String cellPhoneNumber = system.input.nextLine();
        
        // Create Login object
        Login user = new Login(firstName, lastName, username, password, cellPhoneNumber);
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("REGISTRATION RESULT");
        System.out.println("==================================================");
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);
        
        if (registrationMessage.equals("Registration successful.")) {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("LOGIN");
            System.out.println("==================================================");
            
            System.out.print("Enter your username: ");
            String enteredUsername = system.input.nextLine();
            
            System.out.print("Enter your password: ");
            String enteredPassword = system.input.nextLine();
            
            boolean loginSuccessful = user.loginUser(enteredUsername, enteredPassword);
            System.out.println();
            System.out.println(user.returnLoginStatus(loginSuccessful));
        }
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Thank you for using the system. Goodbye!");
        System.out.println("==================================================");
    }
    }