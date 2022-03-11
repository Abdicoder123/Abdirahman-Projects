import java.util.Random;

/**
 * Number guesser is a traditional game for guessing numbers. The game is common
 * enough that you may have even programmed it before! (This is one of Kluver’s
 * go-to games to program when learning a new programming language) The game
 * play starts by selecting a secret number. In the real world one person would
 * pick the number and respond to guesses, we will have the computer do that.
 * After that, players are allowed to guess numbers until they guess the secret
 * number (or hit a maximum number of guesses). After each guess they are told
 * “too high” or “too low” to indicate if the guess was above, or below the
 * secret number.
 */
public class NumberGuesser extends Game {
    private Random rng;
    private int maxNumber;
    private int maxGuesses;
    private int hiddenNumber;
    private int over;
    private int answer;
    private String input;

    public NumberGuesser(Random rng, int maxNumber, int maxGuesses) {
        this.rng = rng;
        this.maxNumber = maxNumber;
        this.maxGuesses = maxGuesses;
    }

    /**
     * Get game name
     * @return the name of the class
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * prepares game by getting a random number
     * @return the number picked and what paremeter it is in
     */
    @Override
    public String prepToPlay() {
        over = 0;
        answer = 0;
        hiddenNumber = 1 + rng.nextInt(maxNumber);
        return "I've picked a number 1 to " + maxNumber + ". You get " + maxGuesses + " guesses to guess it";
    }

    /**Guesses whether the player has picked the right or wrong number
     *Also if the player has taken too many guesses
     * @return boolean True or False
     */
    @Override
    public boolean isOver() {
        if (over >= maxGuesses) {
            return true;
        } else if (hiddenNumber == answer) {
            return true;
        } else if (input == "quit") {
            return true;
        } else {
            return false;
        }
    }

    /**Checks whether the player has a valid guess as an input
     *
     * @param move the input choice from the user
     * @return boolean True or False
     */
    @Override
    public boolean isValid(String move) {
        boolean valid = false;
        input = move;
        try {
            Integer.parseInt(move);
            valid = true;
        } catch (NumberFormatException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Checks if the user picked the right number
     * or if the guess picked was too high or too low
     * @param move the input choice from the user
     * @return If the guess is too high or too low or correct
     */
    @Override
    public String processMove(String move) {
        answer = Integer.parseInt(move);
        over++;
        if (answer > hiddenNumber) {
            return "Too High";
        } else if (answer < hiddenNumber) {
            return "Too Low";
        } else {
            return "That's it!";
        }

    }

    /**
     * Indicate what the hidden number is
     *
     * @return String message detailing what is the secrete number
     */
    @Override
    public String finalMessage() {
        return "The number was: " + hiddenNumber;
    }
}
