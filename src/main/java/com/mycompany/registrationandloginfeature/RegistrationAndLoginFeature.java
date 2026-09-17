/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;

import java.util.Scanner;

public class RegistrationAndLoginFeature {

    private Scanner input = new Scanner(System.in); // scanner for reading user input
    
    public static void main(String[] args) {
        RegistrationAndLoginFeature system = new RegistrationAndLoginFeature(); 
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("WELCOME TO THE REGISTRATION AND LOGIN FEATURE");
        System.out.println("Part 1 of POE");
        System.out.println("==================================================");
        
        System.out.print("\nEnter your first name: ");
        String firstName = system.input.nextLine(); // get first name from user
        
        System.out.print("Enter your last name: ");
        String lastName = system.input.nextLine(); // get last name from user
        
        System.out.print("Enter a username (must contain '_' and be <= 5 characters): ");
        String username = system.input.nextLine(); // get username from user
        
        System.out.print("Enter a password (min 8 chars, 1 capital, 1 number, 1 special char): ");
        String password = system.input.nextLine(); // get password from user
        
        System.out.print("Enter your South African cell phone number (format: +27XXXXXXXXX): ");
        String cellPhoneNumber = system.input.nextLine(); // get cell number from user
        
        // Create Login object
        Login user = new Login(firstName, lastName, username, password, cellPhoneNumber); // pass all details to Login class
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("REGISTRATION RESULT");
        System.out.println("==================================================");
        String registrationMessage = user.registerUser(); // call register method to check all details
        System.out.println(registrationMessage); 
        
        if (registrationMessage.equals("Registration successful.")) { // only login if registration passed
            System.out.println();
            System.out.println("==================================================");
            System.out.println("LOGIN");
            System.out.println("==================================================");
            
            System.out.print("Enter your username: ");
            String enteredUsername = system.input.nextLine(); // get username for login
            
            System.out.print("Enter your password: ");
            String enteredPassword = system.input.nextLine(); // get password for login
            
            boolean loginSuccessful = user.loginUser(enteredUsername, enteredPassword); // check if details match
            System.out.println();
            System.out.println(user.returnLoginStatus(loginSuccessful)); // print welcome or error message
        }
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Thank you for using the system. Goodbye!");
        System.out.println("==================================================");
    }
}