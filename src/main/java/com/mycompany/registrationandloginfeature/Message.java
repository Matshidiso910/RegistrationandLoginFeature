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
    
    String messageID;      // randomly generated 10-digit ID
    int messageNumber;     // the number of this message (0, 1, 2...)
    String recipient;      // recipient cell number with +27
    String messageText;    // the actual message content
    String messageHash;    // format: xx:x:FIRSTLAST
    
    static int totalMessages = 0;  // shared counter across all messages

    public Message(String rec, String msgTxt) {
        recipient = rec;
        messageText = msgTxt;
        messageID = generateMessageID();
        messageNumber = totalMessages;
        messageHash = createMessageHash();
    }

    // generates a random 10-digit number for the message ID
    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // message ID must not be more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // checks that recipient has +27 and 9 digits after it
    public String checkRecipientCell() {
        String cleaned = recipient.replaceAll("[\\s\\-()]", "");
        if (cleaned.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // creates hash: first 2 digits of ID + ":" + message number + ":" + first & last word in caps
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // allows user to send, disregard, or store the message
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessages++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                totalMessages++;
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // returns total number of messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }

    // writes the message to messages.json file
    public void storeMessage() {
        String json = "{\n" +
                      "  \"id\": \"" + messageID + "\",\n" +
                      "  \"hash\": \"" + messageHash + "\",\n" +
                      "  \"recipient\": \"" + recipient + "\",\n" +
                      "  \"message\": \"" + messageText + "\"\n" +
                      "}\n";
        
        try { // try to write the message to the JSON file
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write(json);
            writer.close();
            System.out.println("Message successfully stored to messages.json");
        } catch (IOException e) { // if file writing fails, show an error message
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // getters to read private values from outside the class
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    // resets the counter (used for testing)
    public static void resetMessages() {
        totalMessages = 0;
    }
}
    
    