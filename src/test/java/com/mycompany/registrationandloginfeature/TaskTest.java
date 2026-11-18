/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class TaskTest {
    
    public TaskTest() {
    }
     @BeforeEach
    public void setUp() {
        MessageManager.resetArrays();
        Message.resetMessages();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?", "1111111111");
        Message msg4 = new Message("0838884567", "It is dinner time!", "4444444444");
        
        MessageManager.addMessage("Sent", msg1.getMessageID(), msg1.getMessageHash(), msg1.getRecipient(), msg1.getMessageText());
        MessageManager.addMessage("Sent", msg4.getMessageID(), msg4.getMessageHash(), msg4.getRecipient(), msg4.getMessageText());
        
        String[] expected = {"Did you get the cake?", "It is dinner time!"};
        String[] actual = new String[2];
        System.arraycopy(MessageManager.sentMessages, 0, actual, 0, 2);
        
        assertArrayEquals(expected, actual, "The Sent Messages array should contain the correct test data.");
    }

    @Test
    public void testDisplayLongestMessage() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?", "1111111111");
        MessageManager.addMessage("Stored", msg1.getMessageID(), msg1.getMessageHash(), msg1.getRecipient(), msg1.getMessageText());
        
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "2222222222");
        MessageManager.addMessage("Stored", msg2.getMessageID(), msg2.getMessageHash(), msg2.getRecipient(), msg2.getMessageText());
        
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        String actualLongest = MessageManager.displayLongestMessage();
        
        assertEquals(expectedLongest, actualLongest, "The system should return the longest stored message.");
    }

    @Test
    public void testSearchByMessageID() {
        Message msg4 = new Message("0838884567", "It is dinner time!", "0838884567");
        MessageManager.addMessage("Sent", msg4.getMessageID(), msg4.getMessageHash(), msg4.getRecipient(), msg4.getMessageText());
        
        String searchResult = MessageManager.searchByMessageID("0838884567");
        assertTrue(searchResult.contains("It is dinner time!"), "The system should return the correct message for the ID.");
    }

    @Test
    public void testSearchByRecipient() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "2222222222");
        MessageManager.addMessage("Stored", msg2.getMessageID(), msg2.getMessageHash(), msg2.getRecipient(), msg2.getMessageText());
        
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.", "5555555555");
        MessageManager.addMessage("Stored", msg5.getMessageID(), msg5.getMessageHash(), msg5.getRecipient(), msg5.getMessageText());
        
        String searchResult = MessageManager.searchByRecipient("+27838884567");
        
        assertTrue(searchResult.contains("Where are you? You are late!"), "Should contain Message 2");
        assertTrue(searchResult.contains("Ok, I am leaving without you."), "Should contain Message 5");
    }

    @Test
    public void testDeleteMessageByHash() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "2222222222");
        MessageManager.addMessage("Stored", msg2.getMessageID(), msg2.getMessageHash(), msg2.getRecipient(), msg2.getMessageText());
        
        assertEquals(1, MessageManager.storedCount);
        
        String deleteResult = MessageManager.deleteMessageByHash(msg2.getMessageHash());
        
        assertTrue(deleteResult.contains("successfully deleted"), "The system should confirm deletion.");
        assertEquals(0, MessageManager.storedCount, "The stored count should be 0 after deletion.");
    }

    @Test
    public void testDisplayReport() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?", "1111111111");
        MessageManager.addMessage("Sent", msg1.getMessageID(), msg1.getMessageHash(), msg1.getRecipient(), msg1.getMessageText());
        
        String report = MessageManager.displayReport();
        
        assertTrue(report.contains("[SENT]"), "Report should indicate it is a sent message.");
        assertTrue(report.contains("Did you get the cake?"), "Report should contain the message text.");
        assertTrue(report.contains("+27834557896"), "Report should contain the recipient.");
        assertTrue(report.contains(msg1.getMessageHash()), "Report should contain the message hash.");
    }
}

