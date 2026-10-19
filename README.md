# Registration and Login Feature + QuickChat

## PROG5121 - Portfolio of Evidence (PoE) - Part 1 & Part 2

**Student Name:** Matshidiso Mualeka  
**Student Number:** ST10486704  
**Institution:** The Independent Institute of Education (Pty) Ltd  

---

## Project Description
This is a console-based Java application developed for Programming 1A. It consists of two main parts:
1. **Part 1 (Registration & Login):** A secure user authentication system that validates usernames, passwords, and South African cell phone numbers using Regular Expressions.
2. **Part 2 (QuickChat):** A messaging feature where logged-in users can send, store, or disregard messages. The application automatically generates unique 10-digit message IDs and complex message hashes, and saves stored messages to a JSON file.

---

## Features

### Part 1: Registration and Login
- **Username Validation:** Must contain an underscore (`_`) and be no longer than 5 characters.
- **Password Complexity:** Must be at least 8 characters, contain a capital letter, a number, and a special character.
- **Cell Phone Validation:** Uses a Regular Expression to ensure the number starts with the South African international code (`+27`) followed by 9 digits.
- **Login System:** Verifies the entered credentials against the stored registration details.

### Part 2: QuickChat
- **Menu System:** A looped numeric menu to send messages, view a placeholder for recent messages, or quit.
- **Message Generation:** 
  - Generates a random 10-digit `Message ID`.
  - Creates a `Message Hash` (e.g., `00:0:HITONIGHT`) using string manipulation (first 2 digits of the ID, message number, and the first & last words of the message in uppercase).
  - Validates that messages do not exceed 250 characters.
- **Message Actions:** The user can choose to **Send**, **Disregard** (with a delete option), or **Store** the message.
- **JSON Storage:** Stored messages are successfully saved to a `messages.json` file using `FileWriter`.
- **Unit Testing:** Includes 10 JUnit 5 tests specifically for the `Message` class and 14 tests for the `Login` class, ensuring all logic works perfectly.
- **CI/CD Pipeline:** Automated unit tests run via GitHub Actions (`.github/workflows/maven.yml`) upon every push.

---

## Technologies Used
- **Java (JDK 17)**
- **JUnit 5** (for automated unit testing)
- **Maven** (build automation and dependency management)
- **GitHub Actions** (Continuous Integration)
- **JSON** (for data storage)

---

## How to Run the Application

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/Matshidiso910/RegistrationAndLoginFeature.git
