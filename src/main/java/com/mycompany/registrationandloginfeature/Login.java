/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Login Class - Handles user registration and login functionality
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
 * REFERENCE 4: Java Pattern and Matcher Classes
 * Source: Oracle Java Documentation
 * URL: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * Date Accessed: 2024
 * Accuracy: Official documentation
 */
public class Login {
    
    String firstName;
    String lastName;
    String username;
    String password;
    String cellPhoneNumber;
    
    public Login(String fName, String lName, String uName, String pWord, String phone) {
        firstName = fName;
        lastName = lName;
        username = uName;
        password = pWord;
        cellPhoneNumber = phone;
    }
    
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }
    
 
      public boolean checkPasswordComplexity() {
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        if (password.length() < 8) {
            return false;
        }
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        
        return hasUpperCase && hasDigit && hasSpecialChar;
    }
    
    public boolean checkCellPhoneNumber() {
        String cleanedNumber = cellPhoneNumber.replaceAll("[\\s\\-()]", "");
        Pattern pattern = Pattern.compile("^\\+27\\d{9}$");
        Matcher matcher = pattern.matcher(cleanedNumber);
        return matcher.matches();
    }
    
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        
        return "Registration successful.";
    }
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }
    
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}