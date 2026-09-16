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
public class LoginTest {
    
    public LoginTest() {
    }
   
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        String actual = login.returnLoginStatus(true);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        String expected = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }
    
    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginFailed() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
    
    @Test
    public void testUsernameCorrectlyFormattedTrue() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }
    
    @Test
    public void testUsernameIncorrectlyFormattedFalse() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }
    
    @Test
    public void testPasswordMeetsComplexityTrue() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityFalse() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }
    
    @Test
    public void testCellPhoneCorrectlyFormattedTrue() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormattedFalse() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }
}

