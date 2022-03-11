import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Word Jumble game is another classic “word learning” game. The game starts
 * with the computer selecting a secret word. Then the secret word is randomly
 * shuffled / jumbeled, so that the letters are no longer in the correct order,
 * and the original word is obscured. The player then repeatedly tries to guess
 * what the hidden word is.
 */
public class WordJumble extends Game {
    private WordsList wl;
    private Random rng;
    private int minWordLen;
    private int maxWordLen;
    private int maxGuesses;
    private String secretWord;
    private String jumbledWord;
    private int over;
    private String input;

    public WordJumble(WordsList wl, Random rng, int minWordLen, int maxWordLen, int maxGuesses) {
        this.wl = wl;
        this.rng = rng;
        this.minWordLen = minWordLen;
        this.maxWordLen = maxWordLen;
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
     * Prepares the game for playing randomly shuffling the letters in the secret
     * word
     *
     * @return game instructions and win/lose conditions
     */
    @Override
    public String prepToPlay() {
        secretWord = wl.getWord(minWordLen, maxWordLen);
        over = 0;

        // Word jumbling
        jumbledWord = "";
        List<String> letters = Arrays.asList(secretWord.split(""));
        Collections.shuffle(letters);
        for (String letter : letters) {
            jumbledWord += letter;
        }

        over = 0;
        input = "";
        return "The following is a jumbled up word:" + jumbledWord + " You got " + maxGuesses + " guesses to guess it";
    }

    /**
     * Checks whether the player guesses the hidden word, or if the player has taken
     * too many guesses
     *
     * @return boolean true or false
     */
    @Override
    public boolean isOver() {
        if (over >= maxGuesses || new String(input).equals(secretWord)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the player gives a valid guess as an input
     *
     * @param move the input choice guess from the user
     * @return boolean true or false
     */
    @Override
    public boolean isValid(String move) {
        boolean valid = true;
        input = move;
        return valid;
    }

    /**
     * Checks whether the player guessed the hidden word correctly
     *
     * @param move the input choice guess from the user
     * @return String message whether the player has won, loss or in a Tie
     */
    @Override
    public String processMove(String move) {
        if (new String(move).equals(secretWord)) {
            over++;
            return "";
        } else {
            over++;
            return "That's not it";
        }
    }

    /**
     * Indicate what the hidden word is
     *
     * @return String message detailing what is the secrete word
     */
    @Override
    public String finalMessage() {
        return "The word was " + secretWord;
    }
}
