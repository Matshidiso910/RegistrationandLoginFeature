/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testMessageLength_Success() {
        // Test that a valid message (under 250 chars) returns the correct success message
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        // Test that a message over 250 chars (260) returns the correct error message and calculates the excess
        String longMsg = "a".repeat(260); 
        Message msg = new Message("+27718693002", longMsg);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellCorrectlyFormatted() {
        // Test that a valid SA cell number with +27 returns the success message
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        // Test that an invalid number (no +27) returns the correct error message
        Message msg = new Message("08575975889", "Hi Keegan"); 
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        // This resets the message counter back to 0 for a clean test
        Message.resetMessages(); 
        
        // We pass "00" as the test ID to force the hash to be 00:0:HITONIGHT
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", "00");
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    } 
    
    @Test
    public void testMessageIDCreated() {
        // Test that the generated Message ID is not null and is 10 characters or less
        Message msg = new Message("+27718693002", "Hi Mike");
        assertTrue(msg.checkMessageID());
        assertNotNull(msg.getMessageID());
    }

    @Test
    public void testSentMessageSend() {
        // Test that choosing option 1 (Send) returns the correct success message
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        // Test that choosing option 2 (Disregard) returns the delete prompt message
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        // Test that choosing option 3 (Store) returns the stored success message
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        // Test that the total message counter correctly increments when messages are sent
        Message.resetMessages(); // Reset to 0 first
        
        Message msg1 = new Message("+27718693002", "Msg 1");
        msg1.SentMessage(1); // Send first message (count goes to 1)
        
        Message msg2 = new Message("+27718693002", "Msg 2");
        msg2.SentMessage(1); // Send second message (count goes to 2)
        
        // Assert that the total returned is indeed 2
        assertEquals(2, msg2.returnTotalMessages());
    }
}