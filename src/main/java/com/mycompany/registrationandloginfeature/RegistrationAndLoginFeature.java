/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class RegistrationAndLoginFeature {

    private Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        RegistrationAndLoginFeature system = new RegistrationAndLoginFeature(); 
        
        // Load previously saved messages from the JSON file at startup
        MessageManager.loadMessagesFromJson();
        
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
                int numMessages = 0;
                try {
                    numMessages = Integer.parseInt(system.input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number entered. Defaulting to 1 message.");
                    numMessages = 1;
                }
                
                boolean running = true;
                while (running) {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("4) Stored Messages"); 
                    System.out.println("--------------------------------------------------");
                    System.out.print("Enter your choice: ");
                    
                    String menuChoice = system.input.nextLine().trim();
                    
                    switch (menuChoice) {
                        case "1":
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
                                    i--; 
                                    continue;
                                }
                                
                                System.out.println("Message ID generated: " + msg.getMessageID());
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
                                        MessageManager.addMessage("Sent", msg.getMessageID(), msg.getMessageHash(), msg.getRecipient(), msg.getMessageText());
                                        break;
                                    case "2":
                                        System.out.println(msg.SentMessage(2));
                                        MessageManager.addMessage("Disregard", msg.getMessageID(), msg.getMessageHash(), msg.getRecipient(), msg.getMessageText());
                                        // Note: If you want to prompt for deletion here, add that logic, but it's handled in the menu
                                        break;
                                    case "3":
                                        System.out.println(msg.SentMessage(3));
                                        MessageManager.addMessage("Stored", msg.getMessageID(), msg.getMessageHash(), msg.getRecipient(), msg.getMessageText());
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
                            
                        case "4":
                            boolean subMenuRunning = true;
                            while(subMenuRunning) {
                                System.out.println("\n--- Stored Messages Menu ---");
                                System.out.println("1) Display sender and recipient of all stored messages");
                                System.out.println("2) Display the longest stored message");
                                System.out.println("3) Search by Message ID");
                                System.out.println("4) Search by Recipient");
                                System.out.println("5) Delete by Message Hash");
                                System.out.println("6) Display Full Report");
                                System.out.println("7) Back to Main Menu");
                                System.out.print("Enter choice: ");
                                
                                String subChoice = system.input.nextLine().trim();
                                
                                switch (subChoice) {
                                    case "1":
                                        System.out.println(MessageManager.displayStoredMessages());
                                        break;
                                    case "2":
                                        System.out.println("Longest Message: " + MessageManager.displayLongestMessage());
                                        break;
                                    case "3":
                                        System.out.print("Enter Message ID: ");
                                        String searchId = system.input.nextLine();
                                        System.out.println(MessageManager.searchByMessageID(searchId));
                                        break;
                                    case "4":
                                        System.out.print("Enter Recipient Number: ");
                                        String searchRec = system.input.nextLine();
                                        System.out.println(MessageManager.searchByRecipient(searchRec));
                                        break;
                                    case "5":
                                        System.out.print("Enter Message Hash: ");
                                        String searchHash = system.input.nextLine();
                                        System.out.println(MessageManager.deleteMessageByHash(searchHash));
                                        break;
                                    case "6":
                                        System.out.println(MessageManager.displayReport());
                                        break;
                                    case "7":
                                        subMenuRunning = false;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            break;
                            
                        default:
                            System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    }
                }
            }
        }
        System.out.println("\n==================================================");
        System.out.println("Thank you for using the system. Goodbye!");
        System.out.println("==================================================");
    }
}