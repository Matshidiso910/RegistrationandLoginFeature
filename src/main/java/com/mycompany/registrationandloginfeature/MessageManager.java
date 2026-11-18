/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class MessageManager {
   
   
    // Arrays to store the different types of messages
    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    
    // Parallel arrays to store metadata for each message
    public static String[] messageHashes = new String[100];
    public static String[] messageIDs = new String[100];
    public static String[] messageRecipients = new String[100];
    
    // Variables to keep track of how many messages are in each array
    public static int sentCount = 0;
    public static int disregardCount = 0;
    public static int storedCount = 0;

    // The name of the JSON file where messages are saved
    public static final String JSON_FILE = "messages.json";

    
    public static void addMessage(String type, String id, String hash, String recipient, String text) {
        if (type.equals("Sent")) {
            messageIDs[sentCount] = id;
            messageHashes[sentCount] = hash;
            messageRecipients[sentCount] = recipient;
            sentMessages[sentCount] = text;
            sentCount++;
        } else if (type.equals("Stored")) {
            messageIDs[storedCount] = id;
            messageHashes[storedCount] = hash;
            messageRecipients[storedCount] = recipient;
            storedMessages[storedCount] = text;
            storedCount++;
        } else if (type.equals("Disregard")) {
            disregardedMessages[disregardCount] = text;
            disregardCount++;
        }
        saveMessagesToJson(); // Save to file every time a message is added
    }

    // --- JSON FILE WRITING ---
    
    /**
     * Writes the contents of the arrays to a JSON file.
     */
    public static void saveMessagesToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            writer.write("[\n");
            boolean first = true;
            
            // Write Sent Messages to JSON
            for (int i = 0; i < sentCount; i++) {
                if (!first) writer.write(",\n");
                writer.write("  {\"type\":\"Sent\", \"id\":\"" + messageIDs[i] + "\", \"hash\":\"" + messageHashes[i] + "\", \"recipient\":\"" + messageRecipients[i] + "\", \"message\":\"" + sentMessages[i] + "\"}");
                first = false;
            }
            // Write Stored Messages to JSON
            for (int i = 0; i < storedCount; i++) {
                if (!first) writer.write(",\n");
                writer.write("  {\"type\":\"Stored\", \"id\":\"" + messageIDs[i] + "\", \"hash\":\"" + messageHashes[i] + "\", \"recipient\":\"" + messageRecipients[i] + "\", \"message\":\"" + storedMessages[i] + "\"}");
                first = false;
            }
            
            writer.write("\n]");
        } catch (IOException e) {
            System.out.println("Error saving to JSON file: " + e.getMessage());
        }
    }

    
    public static void loadMessagesFromJson() {
        File file = new File(JSON_FILE);
        if (!file.exists()) return; // If the file doesn't exist, do nothing

        resetArrays(); // Clear existing arrays before loading

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Parse the JSON string manually to extract values
                if (line.contains("\"type\":\"Sent\"")) {
                    String id = extractValue(line, "id");
                    String hash = extractValue(line, "hash");
                    String recipient = extractValue(line, "recipient");
                    String msg = extractValue(line, "message");
                    messageIDs[sentCount] = id;
                    messageHashes[sentCount] = hash;
                    messageRecipients[sentCount] = recipient;
                    sentMessages[sentCount] = msg;
                    sentCount++;
                } else if (line.contains("\"type\":\"Stored\"")) {
                    String id = extractValue(line, "id");
                    String hash = extractValue(line, "hash");
                    String recipient = extractValue(line, "recipient");
                    String msg = extractValue(line, "message");
                    messageIDs[storedCount] = id;
                    messageHashes[storedCount] = hash;
                    messageRecipients[storedCount] = recipient;
                    storedMessages[storedCount] = msg;
                    storedCount++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }

    /**
     * Helper method to extract values from a raw JSON string.
     */
    private static String extractValue(String line, String key) {
        String searchKey = "\"" + key + "\":\"";
        int start = line.indexOf(searchKey) + searchKey.length();
        int end = line.indexOf("\"", start);
        return line.substring(start, end);
    }


    // --- SEARCH & REPORT METHODS ---
     
    public static String displayStoredMessages() { //Displays all messages that have been stored.
        if (storedCount == 0) return "No stored messages.";
        String output = "";
        for (int i = 0; i < storedCount; i++) {
            output += "Sender: You | Recipient: " + messageRecipients[i] + " | Message: " + storedMessages[i] + "\n";
        }
        return output;
    }

    /**
     * Finds and returns the longest message in the stored array.
     */
    public static String displayLongestMessage() {
        if (storedCount == 0) return "No stored messages.";
        String longest = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        return longest;
    }

    /**
     * Searches for a message based on its unique ID.
     */
    public static String searchByMessageID(String id) {
        // Search sent messages first
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Recipient: " + messageRecipients[i] + " | Message: " + sentMessages[i];
            }
        }
        // Search stored messages next
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Recipient: " + messageRecipients[i] + " | Message: " + storedMessages[i];
            }
        }
        return "Message ID not found.";
    }

    /**
     * Searches for all messages sent or stored for a specific recipient.
     */
    public static String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < sentCount; i++) {
            if (messageRecipients[i] != null && messageRecipients[i].equals(recipient)) {
                result += "Sent: " + sentMessages[i] + "\n";
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageRecipients[i] != null && messageRecipients[i].equals(recipient)) {
                result += "Stored: " + storedMessages[i] + "\n";
            }
        }
        return result.isEmpty() ? "No messages found for this recipient." : result;
    }

    /**
     * Deletes a message using its unique hash.
     * It finds the message, shifts the remaining items in the array to fill the gap, and updates the count.
     */
    public static String deleteMessageByHash(String hash) {
        // Check sent messages
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                String deletedMsg = sentMessages[i];
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                    messageRecipients[j] = messageRecipients[j + 1];
                }
                sentCount--;
                saveMessagesToJson(); // Update file after deletion
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        // Check stored messages
        for (int i = 0; i < storedCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                String deletedMsg = storedMessages[i];
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                    messageRecipients[j] = messageRecipients[j + 1];
                }
                storedCount--;
                saveMessagesToJson(); // Update file after deletion
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    /**
     * Generates a formatted report of all sent and stored messages.
     */
    public static String displayReport() {
        String report = "--- Full Message Report ---\n";
        if (sentCount == 0 && storedCount == 0) return report + "No messages to report.";
        
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null) {
                report += "[SENT] Hash: " + messageHashes[i] + " | Recipient: " + messageRecipients[i] + " | Message: " + sentMessages[i] + "\n";
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i] != null) {
                report += "[STORED] Hash: " + messageHashes[i] + " | Recipient: " + messageRecipients[i] + " | Message: " + storedMessages[i] + "\n";
            }
        }
        return report;
    }

    /**
     * Resets all arrays and counters. Used mainly for unit testing to ensure a clean slate.
     */
    public static void resetArrays() {
        sentCount = 0;
        disregardCount = 0;
        storedCount = 0;
        for (int i = 0; i < 100; i++) {
            sentMessages[i] = null;
            disregardedMessages[i] = null;
            storedMessages[i] = null;
            messageHashes[i] = null;
            messageIDs[i] = null;
            messageRecipients[i] = null;
        }
    }
}