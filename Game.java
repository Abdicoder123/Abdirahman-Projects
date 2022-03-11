//Abdirahman Ali
//CSCI 1913, Project 2
//Daniel Kluver
import java.util.Scanner;

/**
 * The game class is an abstract class that is designed to be a parent class to
 * the four actual game implementations.
 */
public abstract class Game {
    protected abstract String prepToPlay();

    protected abstract boolean isOver();

    protected abstract boolean isValid(String move);

    protected abstract String processMove(String move);

    protected abstract String finalMessage();

    public abstract String getName();

    /**
     * This a general outline for all the games
     * @param user The inputs from the user himself
     */
    public void play(Scanner user) {
        String input;
        System.out.println(prepToPlay());
        while (isOver() == false) {
            System.out.print("Enter Your Move or 'quit' to quit> ");
            input = user.next();
            if (input.equals("quit")) {
                System.out.println(finalMessage());
                return;
            }
            if (isValid(input) == false) {
                while (isValid(input) == false) {
                    System.out.print("Invalid Move! try again> ");
                    input = user.next();
                    if (isValid(input) == true) {
                        String rst = processMove(input);
                        if (rst == null) {
                            System.out.println(finalMessage());
                            return;
                        } else {
                            System.out.println(rst);
                        }
                    }
                }
            } else {
                String rst = processMove(input);
                if (rst == null) {
                    System.out.println(finalMessage());
                    return;
                } else {
                    System.out.println(rst);
                }
            }

        }
        System.out.println(finalMessage());
    }
}
