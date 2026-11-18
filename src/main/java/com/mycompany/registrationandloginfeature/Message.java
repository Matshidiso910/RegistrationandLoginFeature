/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class Message {
    
    // --- Class Variables ---
    private String messageID;       // Randomly generated 10-digit ID
    private int messageNumber;      // The index number of the message (0, 1, 2...)
    private String recipient;       // The cell phone number the message is sent to
    private String messageText;     // The actual content of the message
    private String messageHash;     // A generated hash for verification (e.g., 00:0:HITONIGHT)
    
    // Static variable to keep track of the total messages across all instances
    static int totalMessages = 0;

    // --- Constructors ---

    // Standard constructor used when a user creates a new message
    public Message(String rec, String msgTxt) {
        this.recipient = rec;
        this.messageText = msgTxt;
        this.messageID = generateMessageID();
        this.messageNumber = totalMessages;
        this.messageHash = createMessageHash();
    }

    // Special constructor used ONLY for unit testing (allows us to force a specific ID)
    public Message(String rec, String msgTxt, String testID) {
        this.recipient = rec;
        this.messageText = msgTxt;
        this.messageID = testID;
        this.messageNumber = totalMessages;
        this.messageHash = createMessageHash();
    }

    // --- Methods ---

    /**
     * Generates a random 10-digit number for the Message ID.
     * @return String representation of the ID
     */
    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    /**
     * Checks if the generated Message ID is valid (not more than 10 characters).
     * @return true if valid, false otherwise
     */
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    /**
     * Checks if the recipient's cell phone number is correctly formatted.
     * Must contain the South African international code (+27) and 9 digits.
     * @return Success or error message
     */
    public String checkRecipientCell() {
        // Remove any spaces, dashes, or brackets from the input
        String cleaned = recipient.replaceAll("[\\s\\-()]", "");
        
        // Check if it matches the +27XXXXXXXXX format using Regex
        if (cleaned.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    /**
     * Checks if the message exceeds the 250 character limit.
     * @return Success message or an error stating how many characters to remove
     */
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        } else {
            return "Message ready to send.";
        }
    }

    /**
     * Creates a message hash using string manipulation.
     * Format: first 2 digits of ID + ":" + message number + ":" + first & last word in caps.
     * @return The generated hash string
     */
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        
        // Remove punctuation from the message so words are clean
        String cleanText = messageText.replaceAll("[^a-zA-Z ]", ""); 
        String[] words = cleanText.trim().split("\\s+");
        
        // Get the first and last words and convert them to uppercase
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    /**
     * Handles the user's choice to Send, Disregard, or Store the message.
     * @param choice The menu number selected by the user
     * @return The appropriate success message
     */
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessages++; // Increment the message counter
                return "Message successfully sent.";
            case 2:
                totalMessages++;
                return "Press 0 to delete the message.";
            case 3:
                storeMessage(); // Call the method to write to JSON
                totalMessages++; 
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    /**
     * Returns the total number of messages processed.
     * @return totalMessages
     */
    public int returnTotalMessages() {
        return totalMessages;
    }

    /**
     * Writes the message details to a JSON file.
     */
    public void storeMessage() {
        // Construct the JSON string with proper formatting
        String json = "{\n" +
                      "  \"id\": \"" + messageID + "\",\n" +
                      "  \"messageNumber\": " + messageNumber + ",\n" +
                      "  \"hash\": \"" + messageHash + "\",\n" +
                      "  \"recipient\": \"" + recipient + "\",\n" +
                      "  \"message\": \"" + messageText + "\"\n" +
                      "}\n";
        
        try {
            // Open the file in append mode ('true' prevents overwriting existing messages)
            FileWriter writer = new FileWriter("messages.json", true); 
            writer.write(json);
            writer.close();
            System.out.println("Message successfully stored to messages.json");
        } catch (IOException e) {
            // Catch any file writing errors (like permission issues)
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // --- Getters (Used by other classes to read private variables) ---
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
    
    // Resets the static counter (mostly used for unit testing)
    public static void resetMessages() { totalMessages = 0; }
}