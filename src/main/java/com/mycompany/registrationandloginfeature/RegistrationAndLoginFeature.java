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
            
            if (loginSuccessful) {
                System.out.println();
                System.out.println("==================================================");
                System.out.println("Welcome to QuickChat.");
                System.out.println("==================================================");
                
                int numMessages = 0;
                System.out.print("How many messages do you wish to enter? ");
                try {
                    numMessages = Integer.parseInt(system.input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Exiting.");
                    return;
                }
                
                boolean running = true;
                while (running) {
                    System.out.println();
                    System.out.println("--------------------------------------------------");
                    System.out.println("Please choose an option:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Enter your choice: ");
                    
                    String menuChoice = system.input.nextLine().trim();
                    
                    switch (menuChoice) {
                        case "1":
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println();
                                System.out.println("--- Message " + (i + 1) + " of " + numMessages + " ---");
                                
                                System.out.print("Enter recipient cell number (format: +27XXXXXXXXX): ");
                                String recipient = system.input.nextLine();
                                
                                System.out.print("Enter message (max 250 characters): ");
                                String messageText = system.input.nextLine();
                                
                                if (messageText.length() > 250) {
                                    System.out.println("Please enter a message of less than 250 characters.");
                                    i--;
                                    continue;
                                }
                                
                                Message msg = new Message(recipient, messageText);
                                
                                if (msg.checkMessageID()) {
                                    System.out.println("Message ID generated: " + msg.getMessageID());
                                } else {
                                    System.out.println("Message ID is invalid.");
                                    i--;
                                    continue;
                                }
                                
                                System.out.println(msg.checkRecipientCell());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                
                                System.out.println();
                                System.out.println("Choose an option:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");
                                System.out.print("Enter your choice: ");
                                
                                int sendChoice = 0;
                                try {
                                    sendChoice = Integer.parseInt(system.input.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid choice.");
                                    i--;
                                    continue;
                                }
                                
                                String result = msg.SentMessage(sendChoice);
                                System.out.println(result);
                                
                                if (sendChoice == 1 || sendChoice == 3) {
                                    System.out.println();
                                    System.out.println("--- Message Details ---");
                                    System.out.println("Message ID: " + msg.getMessageID());
                                    System.out.println("Message Hash: " + msg.getMessageHash());
                                    System.out.println("Recipient: " + msg.getRecipient());
                                    System.out.println("Message: " + msg.getMessageText());
                                }
                            }
                            
                            System.out.println();
                            System.out.println("Total messages sent: " + Message.totalMessages);
                            break;
                        
                        case "2":
                            System.out.println("Coming Soon.");
                            break;
                        
                        case "3":
                            System.out.println("Thank you for using QuickChat. Goodbye!");
                            running = false;
                            break;
                        
                        default:
                            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                            break;
                    }
                }
            }
        }
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Thank you for using the system. Goodbye!");
        System.out.println("==================================================");
    }
}