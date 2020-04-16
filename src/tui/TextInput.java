package tui;

import java.util.Scanner;

/**
 * The TextInput is a class that creates a scanner for the user input
 * and handles incorrect inputs.
 *
 * @author dmai0919/Group3
 * @version 1.0
 * @since 2019-11-25
 */

public class TextInput {
    /**
     * Constructor for objects of TextInput
     */
    public TextInput() {
    }

    //other methods

    /**
     * Method for checking whether the input is valid
     *
     * @param question the question to be answered
     * @return number the user input
     */

    public static int inputNumber(String question) {
        Scanner keyboard = new Scanner(System.in);
        int number;
        printQuestion(question);
        while (!keyboard.hasNextInt()) {
            System.out.println(" Input must be a number - try again!");
            System.out.print(" Input number: ");
            keyboard.nextLine();
        }
        number = keyboard.nextInt();
        return number;
    }

    public static double inputDouble(String question) {
        Scanner keyboard = new Scanner(System.in);
        double number;
        printQuestion(question);
        while (!keyboard.hasNextDouble()) {
            System.out.println(" Input must be a number - try again!");
            System.out.print(" Input number: ");
            keyboard.nextLine();
        }
        number = keyboard.nextDouble();
        return number;
    }

    /**
     * Method for inputting a String input
     *
     * @param question the question to be answered
     * @return String the user input of type string
     */

    public static String inputString(String question) {
        Scanner keyboard = new Scanner(System.in);
        printQuestion(question);
        return keyboard.nextLine();
    }

    /**
     * Method for printing the question.
     */

    private static void printQuestion(String question) {
        System.out.print(" " + question + ": ");
    }

}
