Matshidiso Maluleka
ST10486709
# QuickChat Application - POE (Part 3)

 Project Overview
This is the final Portfolio of Evidence (POE) for the QuickChat application. This project builds upon Parts 1 and 2, finalizing the registration and login features, implementing message storage using parallel arrays, reading and writing data to a JSON file, and automating unit tests via GitHub Actions.

 Features
*   **User Registration & Login:** Validates usernames, passwords, and South African cell phone numbers.
*   **Message Management:** Allows users to Send, Store, or Disregard messages.
*   **Validation:** Ensures messages do not exceed 250 characters and cell numbers follow the `+27XXXXXXXXX` format.
*   **Parallel Arrays:** Uses standard Java arrays (no ArrayLists) to store Sent, Stored, and Disregarded messages, alongside their IDs, Hashes, and Recipients.
*   **JSON File I/O:** Automatically saves messages to a `messages.json` file and reads them back into the arrays upon startup.
*   **Stored Messages Menu:** Provides options to:
    *   Display sender and recipient of stored messages.
    *   Find the longest stored message.
    *   Search by Message ID.
    *   Search by Recipient.
    *   Delete a message by its Hash.
    *   Generate a Full Report.
*   **Automated Unit Tests:** Fully tested using JUnit 5 and integrated into GitHub Actions.
 Technologies Used
*   **Java:** Core programming language.
*   **Maven:** Dependency management and build tool.
*   **JUnit 5:** Unit testing framework.
*   **GitHub Actions:** CI/CD pipeline for automated testing.
*   **JSON:** Data storage format.
