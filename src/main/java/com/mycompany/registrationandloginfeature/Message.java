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
}
    