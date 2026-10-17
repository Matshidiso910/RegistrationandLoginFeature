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
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        String longMsg = "a".repeat(260); 
        Message msg = new Message("+27718693002", longMsg);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellCorrectlyFormatted() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        Message msg = new Message("08575975889", "Hi Keegan"); 
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

       @Test
    public void testMessageHashCorrect() {
        // This resets the message counter back to 0 for a clean test
        Message.resetMessages(); 
        
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", "00");
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    } 
    
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertTrue(msg.checkMessageID());
        assertNotNull(msg.getMessageID());
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        Message.resetMessages();
        Message msg1 = new Message("+27718693002", "Msg 1");
        msg1.SentMessage(1);
        Message msg2 = new Message("+27718693002", "Msg 2");
        msg2.SentMessage(1);
        assertEquals(2, msg2.returnTotalMessages());
    }
}