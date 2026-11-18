/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

/**
 *
 * @author Student
 */
public class MessageManager {
   

    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] messageHashes = new String[100];
    public static String[] messageIDs = new String[100];
    public static String[] messageRecipients = new String[100];
    
    public static int sentCount = 0;
    public static int disregardCount = 0;
    public static int storedCount = 0;

    // --- ADD METHODS ---
    
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
    }

    // --- SEARCH & REPORT METHODS ---

    // 1. Display all stored messages
    public static String displayStoredMessages() {
        if (storedCount == 0) return "No stored messages.";
        String output = "";
        for (int i = 0; i < storedCount; i++) {
            output += "Message: " + storedMessages[i] + "\n";
        }
        return output;
    }

    // 2. Display longest message
    public static String displayLongestMessage() {
        if (storedCount == 0) return "No stored messages.";
        String longest = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        return longest;
    }

    // 3. Search by Message ID
    public static String searchByMessageID(String id) {
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Recipient: " + messageRecipients[i] + " | Message: " + sentMessages[i];
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Recipient: " + messageRecipients[i] + " | Message: " + storedMessages[i];
            }
        }
        return "Message ID not found.";
    }

    // 4. Search by Recipient
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

    // 5. Delete by Hash
    public static String deleteMessageByHash(String hash) {
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
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
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
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // 6. Display Full Report
    public static String displayReport() {
        String report = "--- Full Message Report ---\n";
        if (sentCount == 0 && storedCount == 0) return report + "No messages to report.";
        
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null) {
                report += "[SENT] Hash: " + messageHashes[i] + " | To: " + messageRecipients[i] + " | Message: " + sentMessages[i] + "\n";
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i] != null) {
                report += "[STORED] Hash: " + messageHashes[i] + " | To: " + messageRecipients[i] + " | Message: " + storedMessages[i] + "\n";
            }
        }
        return report;
    }

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