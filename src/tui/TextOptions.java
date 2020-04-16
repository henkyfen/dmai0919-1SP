package tui;

import java.util.ArrayList;

/**
 * The TextOptions is a class that creates all the menu options
 * for the user to choose from.
 *
 * @author dmai0919/Group3
 * @version 1.0
 * @since 2019-11-25
 */
public class TextOptions {
    // instance variables 
    private final ArrayList<String> options;
    private final String title;
    private boolean cancellable;


    /**
     * Constructor for objects of TextOptions
     */
    public TextOptions(String title, String cancelText) {
        // initialise instance variables
        options = new ArrayList<>();
        this.title = title;
        if (cancelText != null) {
            cancellable = true;
        }
        addOption(cancelText);
    }

    public TextOptions(String title) {
        this(title, null);
    }

    /**
     * Method for adding and option to the list of options
     */

    public void addOption(String option) {
        options.add(option);
    }

    /**
     * Method prompting a correct input from the user
     *
     * @return choice user input
     */

    public int prompt() {
        //System.out.print('\u000C');
        System.out.println(title);
        int size = options.size();
        for (int i = 0; i < size; i++) {
            String currentOption = options.get(i);
            if (currentOption != null) {
                System.out.println(" [" + i + "]\t" + currentOption);
            }
        }
        int choice;
        int lowerBound = 0;
        if (!cancellable) {
            lowerBound = 1;
        }
        choice = TextInput.inputNumber("Input number");
        while (choice < lowerBound || choice >= size) {
            System.out.println(" Please choose a valid option!");
            choice = TextInput.inputNumber("Input number");
        }
        return choice;
    }
    //other methods
}
