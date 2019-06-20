package phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;


public class PhoneBook {
    private Map<String, PhoneNumber> phoneBook = new TreeMap<>();
    private List<Pattern> patterns = new ArrayList<>();
    private int callsCount = 0;


    public PhoneBook() {
        patterns.add(Pattern.compile("(\\+359)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(0)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(00359)(87|88|89)([2-9])([0-9]{6})"));
    }

    public Map<String, PhoneNumber> getPhoneBook() {
        return phoneBook;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public int getCallsCount() {
        return callsCount;
    }

    public boolean addNewPair(String name, String phone) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                if (!matcher.group(1).equals("+359")) {
                    phone = phone.replaceFirst(matcher.group(1), "+359");
                }
                phoneBook.put(name, new PhoneNumber(phone));
                return true;
            }
        }
        System.out.println("The phone number " + phone + " isn't real number with name "+ name +".");
        return false;
    }

    public boolean deletePair(String name) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            return true;
        } else {
            System.out.println("The name " + name + " isn't in the phoneBook.");
            return false;
        }
    }

    public String accessPhoneNumber(String name) {
        PhoneNumber phoneNumber = phoneBook.get(name);
        if (phoneNumber == null) {
            return "";
        }
        phoneNumber.setCallsCount(phoneNumber.getCallsCount() + 1);
        callsCount++;
        return phoneNumber.getPhoneNumber();
    }

    public void printAllSorted() {
        for (Map.Entry<String, PhoneNumber> entry : phoneBook.entrySet()) {
            System.out.println("Name: " + entry.getKey()
                    + " Phone: " + entry.getValue().getPhoneNumber());
        }
        System.out.println("All calls: " + callsCount);
    }

    public boolean readFromFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] pair = line.replaceAll("\\s+", "").split(",");
                    pair[0] = String.join(" ", pair[0].split("(?=\\p{Upper})"));
                    if (pair.length == 2) {
                        addNewPair(pair[0], pair[1]);
                    } else {
                        System.out.println(String.join(", ", line) + " is incorrect record");
                    }
                } catch (PatternSyntaxException e) {
                    System.out.println(String.join(", ", line) + " is incorrect record and is ignored");
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getTopFive() {
        List<PhoneNumber> phoneNumbers = getSortedPhoneNumber();

        List<PhoneNumber> topFive = phoneNumbers.subList(Math.max(phoneNumbers.size() - 5, 0), phoneNumbers.size());

        System.out.println("The five top: ");
        for (PhoneNumber phoneNumber : topFive) {
            System.out.println("Name: " + phoneBook.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(entry.getValue(), phoneNumber))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()).get(0) +
                    " Phone: " + phoneNumber.getPhoneNumber() + " Count: " + phoneNumber.getCallsCount());
        }
    }

    public List<PhoneNumber> getSortedPhoneNumber() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>(phoneBook.values());
        phoneNumbers.sort(PhoneNumber::compareTo);
        return phoneNumbers;
    }
}
