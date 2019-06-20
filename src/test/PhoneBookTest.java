package test;

import phonebook.PhoneBook;
import phonebook.PhoneNumber;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    private PhoneBook phoneBook = new PhoneBook();

    @org.junit.jupiter.api.Test
    void addNewPair() {
        assertTrue(phoneBook.addNewPair("First Last", "0884532133"),
                "deletePair doesn't work right");
    }

    @org.junit.jupiter.api.Test
    void deletePair() {
        phoneBook.addNewPair("First Last", "0884532133");
        assertTrue(phoneBook.deletePair("First Last")
                , "deletePair doesn't work right");
    }

    @org.junit.jupiter.api.Test
    void accessPhoneNumber() {
        phoneBook.addNewPair("First Last", "0884532133");
        assertNotEquals(phoneBook.accessPhoneNumber("First Last"), "",
                "accessPhoneNumber doesn't work right");
    }

    @org.junit.jupiter.api.Test
    void readFromFile() {
        assertTrue(phoneBook.readFromFile("testFile.txt"), "readFromFile doesn't work right");
    }

    @org.junit.jupiter.api.Test
    void getTopFive() {
    }

    @org.junit.jupiter.api.Test
    void getSortedPhoneNumber() {
        phoneBook.readFromFile("testFile.txt");
        for (int i = 0; i < 20; i++) {
            phoneBook.accessPhoneNumber("Doncho Gluharov");
            if (i % 2 == 0) {
                phoneBook.accessPhoneNumber("Ivet Georgieva");
            }
            if (i % 3 == 0) {
                phoneBook.accessPhoneNumber("Nikol Ivanova");
            }
        }
        assertTrue(isSortedNumbers(phoneBook.getSortedPhoneNumber()));
    }

    private boolean isSortedNumbers(List<PhoneNumber> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getCallsCount() < list.get(i - 1).getCallsCount()) {
                return false;
            }
        }
        return true;
    }
}