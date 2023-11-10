import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                validInput = true;
            } else {
                pipe.next(); // Clear invalid input
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return result;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                validInput = true;
            } else {
                pipe.next(); // Clear invalid input
                System.out.println("Invalid input. Please enter a double.");
            }
        }
        return result;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int result;
        boolean validInput = false;

        while (true) {
            result = getInt(pipe, prompt + " [" + low + " - " + high + "]");
            if (result >= low && result <= high) {
                validInput = true;
                break;
            } else {
                System.out.println("Input must be within the range [" + low + " - " + high + "].");
            }
        }
        return result;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result;
        boolean validInput = false;

        while (true) {
            result = getDouble(pipe, prompt + " [" + low + " - " + high + "]");
            if (result >= low && result <= high) {
                validInput = true;
                break;
            } else {
                System.out.println("Input must be within the range [" + low + " - " + high + "].");
            }
        }
        return result;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input;
        boolean validInput = false;

        while (true) {
            input = getNonZeroLenString(pipe, prompt + " [Y/N]").toUpperCase();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }

    public static String getRegExString(Scanner pipe, String prompt, String regex) {
        String result;
        boolean validInput = false;

        while (true) {
            result = getNonZeroLenString(pipe, prompt);
            if (result.matches(regex)) {
                validInput = true;
                break;
            } else {
                System.out.println("Invalid input. Please match the pattern: " + regex);
            }
        }
        return result;
    }
}
