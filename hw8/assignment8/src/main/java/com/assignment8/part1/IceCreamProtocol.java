package hw8.assignment8.src.main.java.com.assignment8.part1;

public class IceCreamProtocol {
    private static final int WAITING = 0;
    private static final int SENTICECREAM = 1;
    private static final int ANOTHER = 2;

    private int state = WAITING;


    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "What flavor ice cream would you like?";
            state = SENTICECREAM;
        } else if (state == SENTICECREAM) {
            if (theInput.equalsIgnoreCase("Vanilla")) {
                theOutput = "Vanilla is $2.00.";
                String[] input = theInput.split("$");
                int price = Integer.parseInt(input[1]);
                if (price == 2) {
                    theOutput = "Great!";
                    state = ANOTHER;
                } else if (price < 2) {
                    theOutput = "Not enough money. Please enter $2.00.";
                } else {
                    theOutput = price - 2 + " is your change.";
                }
            } else if (theInput.equalsIgnoreCase("Chocolate")) {
                theOutput = "Chocolate is $2.00.";
                String[] input = theInput.split("$");
                int price = Integer.parseInt(input[1]);
                if (price == 2) {
                    theOutput = "Great!";
                    state = ANOTHER;
                } else if (price < 2) {
                    theOutput = "Not enough money. Please enter $2.00.";
                } else {
                    theOutput = price - 2 + " is your change.";
                }
            } else if (theInput.equalsIgnoreCase("Lemon")) {
                theOutput = "Strawberry is $1.00.";
                String[] input = theInput.split("$");
                int price = Integer.parseInt(input[1]);
                if (price == 1) {
                    theOutput = "Great!";
                    state = ANOTHER;
                } else if (price < 1) {
                    theOutput = "Not enough money. Please enter $2.00.";
                } else {
                    theOutput = price - 1 + " is your change.";
                }
            } else {
                theOutput = "We don't have that flavor. Please choose another flavor.";
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("yes")) {
                theOutput = "What flavor ice cream would you like?";
                state = SENTICECREAM;
            } else {
                theOutput = "Bye";
                state = WAITING;
            }
        }

        return theOutput;
    }
}
