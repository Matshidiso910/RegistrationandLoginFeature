/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;

import java.util.Scanner;

public class RegistrationAndLoginFeature {

    private Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        RegistrationAndLoginFeature system = new RegistrationAndLoginFeature(); 
        
        System.out.println("\n==================================================");
        System.out.println("WELCOME TO THE REGISTRATION AND LOGIN FEATURE");
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
        
        Login user = new Login(firstName, lastName, username, password, cellPhoneNumber);
        
        System.out.println("\n==================================================");
        System.out.println("REGISTRATION RESULT");
        System.out.println("==================================================");
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage); 
        
        if (registrationMessage.equals("Registration successful.")) {
            System.out.println("\n==================================================");
            System.out.println("LOGIN");
            System.out.println("==================================================");
            
            System.out.print("Enter your username: ");
            String enteredUsername = system.input.nextLine();
            
            System.out.print("Enter your password: ");
            String enteredPassword = system.input.nextLine();
            
            boolean loginSuccessful = user.loginUser(enteredUsername, enteredPassword);
            System.out.println("\n" + user.returnLoginStatus(loginSuccessful));
            
            if (loginSuccessful) {
                System.out.println("\n==================================================");
                System.out.println("Welcome to QuickChat.");
                System.out.println("==================================================");
                
                System.out.print("How many messages do you wish to enter? ");
                int numMessages = Integer.parseInt(system.input.nextLine());
                
                boolean running = true;
                while (running) {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Enter your choice: ");
                    
                    String menuChoice = system.input.nextLine().trim();
                    
                    switch (menuChoice) {
                        case "1":
                            // For loop handles the number of messages requested
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                                
                                System.out.print("Enter recipient cell number (format: +27XXXXXXXXX): ");
                                String recipient = system.input.nextLine();
                                
                                System.out.print("Enter message (max 250 characters): ");
                                String messageText = system.input.nextLine();
                                
                                Message msg = new Message(recipient, messageText);
                                
                                String lengthCheck = msg.checkMessageLength();
                                if (!lengthCheck.equals("Message ready to send.")) {
                                    System.out.println(lengthCheck);
                                    i--; // Allows user to retry the message
                                    continue;
                                }
                                
                                if (msg.checkMessageID()) {
                                    System.out.println("Message ID generated: " + msg.getMessageID());
                                } else {
                                    System.out.println("Message ID is invalid.");
                                    i--; 
                                    continue;
                                }
                                
                                System.out.println(msg.checkRecipientCell());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                
                                System.out.println("\nChoose an option:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");
                                System.out.print("Enter your choice: ");
                                
                                String sendChoice = system.input.nextLine().trim();
                                
                                switch (sendChoice) {
                                    case "1":
                                        System.out.println(msg.SentMessage(1));
                                        System.out.println("\n--- Message Details ---");
                                        System.out.println("Message Number: " + msg.getMessageNumber()); // Shows loop counter
                                        System.out.println("Message ID: " + msg.getMessageID());
                                        System.out.println("Message Hash: " + msg.getMessageHash());
                                        System.out.println("Recipient: " + msg.getRecipient());
                                        System.out.println("Message: " + msg.getMessageText());
                                        break;
                                        
                                    case "2":
                                        System.out.println(msg.SentMessage(2)); // This prints "Press 0 to delete the message."
                                        String deleteChoice = system.input.nextLine().trim(); // Waits for your input
                                        if (deleteChoice.equals("0")) {
                                            System.out.println("Message successfully deleted.");
                                        } else {
                                            System.out.println("Message kept.");
                                        }
                                        break;
                                        
                                    case "3":
                                        System.out.println(msg.SentMessage(3));
                                        System.out.println("\n--- Message Details ---");
                                        System.out.println("Message Number: " + msg.getMessageNumber()); // Shows loop counter
                                        System.out.println("Message ID: " + msg.getMessageID());
                                        System.out.println("Message Hash: " + msg.getMessageHash());
                                        System.out.println("Recipient: " + msg.getRecipient());
                                        System.out.println("Message: " + msg.getMessageText());
                                        break;
                                        
                                    default:
                                        System.out.println("Invalid choice.");
                                        i--; 
                                        break;
                                }
                            }
                            System.out.println("\nTotal messages sent: " + Message.totalMessages);
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
                    }
                }
            }
        }
        System.out.println("\n==================================================");
        System.out.println("Thank you for using the system. Goodbye!");
        System.out.println("==================================================");
    }
}