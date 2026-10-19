/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    static int totalMessages = 0;

    public Message(String rec, String msgTxt) {
        this.recipient = rec;
        this.messageText = msgTxt;
        this.messageID = generateMessageID();
        this.messageNumber = totalMessages; // The message number increments as the loop runs
        this.messageHash = createMessageHash();
    }

    // Special constructor used ONLY for testing the specific hash
    public Message(String rec, String msgTxt, String testID) {
        this.recipient = rec;
        this.messageText = msgTxt;
        this.messageID = testID;
        this.messageNumber = totalMessages;
        this.messageHash = createMessageHash();
    }

    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        String cleaned = recipient.replaceAll("[\\s\\-()]", "");
        if (cleaned.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (messageText.length() > 250) {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        } else {
            return "Message ready to send.";
        }
    }

    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        
        // Remove all punctuation (like commas and question marks) from the message
        String cleanText = messageText.replaceAll("[^a-zA-Z ]", ""); 
        
        // Split the clean text into words
        String[] words = cleanText.trim().split("\\s+");
        
        // Get the first word and the last word, and make them uppercase
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // This method uses a switch case to decide what happens to the message based on the user's menu choice
    public String SentMessage(int choice) {
        // The switch checks the integer 'choice' and matches it to the correct case
        switch (choice) {
            case 1:
                // If the user chose 1 (Send), increment total messages and return the success message
                totalMessages++; 
                return "Message successfully sent.";
            case 2:
                // If the user chose 2 (Disregard), return the delete prompt message
                return "Press 0 to delete the message.";
            case 3:
                // If the user chose 3 (Store), call the JSON method, increment total messages, and return success
                storeMessage();
                totalMessages++; 
                return "Message successfully stored.";
            default:
                // If the user types a number that isn't 1, 2, or 3, the default case catches it
                return "Invalid choice.";
        }
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

    // Writes messages to JSON file with proper indentation
    public void storeMessage() {
        String json = "{\n" +
                      "  \"id\": \"" + messageID + "\",\n" +
                      "  \"messageNumber\": " + messageNumber + ",\n" +
                      "  \"hash\": \"" + messageHash + "\",\n" +
                      "  \"recipient\": \"" + recipient + "\",\n" +
                      "  \"message\": \"" + messageText + "\"\n" +
                      "}\n";
        
        try {
            // 'true' means it will append to the file, not overwrite it
            FileWriter writer = new FileWriter("messages.json", true); 
            writer.write(json);
            writer.close();
            System.out.println("Message successfully stored to messages.json");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
    
    public static void resetMessages() { totalMessages = 0; }
}