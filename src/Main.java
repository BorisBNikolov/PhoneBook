import phonebook.PhoneBook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        //adding some data
        //phoneBook.readFromFile("testFile.txt");

        final Scanner scan = new Scanner(System.in);

        System.out.println("List of the commands:");
        for (Command command : Command.values()) {
            System.out.println(command.getLabelNumber() + "." + command.getLabel());
        }

        System.out.println("Instructions: To choose command write the number of the command");
        System.out.println("then the arguments if the command need it splitted only with ','");
        System.out.println("Example:2,Georgi Georgiev,0882451234");
        System.out.println("Second example:4");

        String line = scan.nextLine();
        while (true) {

            final String[] command = line.split("[,]");
            Command thisCommand = findCommand(command[0]);

                if (thisCommand != null) {
                    thisCommand.invoke(phoneBook, command);
                } else {
                    System.err.println("Command not found");
                    System.exit(1);
                }

            line = scan.nextLine();
        }
    }

    private static Command findCommand(final String labelNumber) {
        for (Command command : Command.values()) {
            if ((command.getLabelNumber().toString().equals(labelNumber))) {
                return command;
            }
        }
        return null;
    }
}
