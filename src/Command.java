import phonebook.PhoneBook;

public enum Command {
    READ_FROM_FILE(1, "Read from file") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 2)) {
                phoneBook.readFromFile(arguments[1]);
            } else {
                System.err.println("Couldn't read the file");
                System.exit(1);
            }

        }
    },
    ADD_NEW_PAIR(2, "Add new pair") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 3)) {
                phoneBook.addNewPair(arguments[1], arguments[2]);
            } else {
                System.err.println("Couldn't add the pair");
                System.exit(1);
            }

        }
    },
    DELETE_PAIR(3, "Delete pair") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 2)) {
                phoneBook.deletePair(arguments[1]);
            } else {
                System.err.println("Couldn't delete the pair");
                System.exit(1);
            }

        }
    },
    PRINT_ALL_SORTED(4, "Print all sorted") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 1)) {
                phoneBook.printAllSorted();
            } else {
                System.err.println("Couldn't print");
                System.exit(1);
            }

        }
    },
    GET_TOP_FIVE(5, "Get top five") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 1)) {
                phoneBook.getTopFive();
            } else {
                System.err.println("Couldn't find the active ads by category");
                System.exit(1);
            }

        }
    },
    EXIT(6, "Exit") {
        @Override
        public void invoke(PhoneBook phoneBook, String[] arguments) {
            if (validateArgumentsOfInvoke(phoneBook, arguments, 1)) {
                System.exit(0);
            } else {
                System.err.println("Couldn't exit");
                System.exit(1);
            }

        }
    };

    private final String label;

    private final Integer labelNumber;

    Command(final int labelNumber, final String label) {
        this.label = label;
        this.labelNumber = labelNumber;
    }

    public String getLabel() {
        return label;
    }


    public Integer getLabelNumber() {
        return labelNumber;
    }

    public abstract void invoke(PhoneBook phoneBook, String[] arguments);

    private static boolean validateArgumentsOfInvoke(final PhoneBook phoneBook, final String[] arguments, int n) {
        return phoneBook != null && arguments != null && arguments.length == n;
    }
}
