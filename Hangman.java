/**
 * Hangman” is the rather morbid name for a traditional “word learning” game,
 * commonly played in grade school. The game starts by selecting a word in
 * secret (this would be done by a teacher in a school setting, but will be done
 * by the Hangman class in our case) The players are told how long this word is,
 * often by showing a series of blanks: _______ (a 7 letter word) The players
 * then are asked to guess letters that may be in the word. If the user guesses
 * a letter that is in the word, then all of the places in the word with that
 * letter are revealed: _a___a_ (guess “a”) _an__an (guess “n”) han__an (guess
 * “h”) If the user guesses wrong, then no new information is revealed: han__an
 * (guess “d”)
 */
public class Hangman extends Game {
    private WordsList words;
    private int minWordLen;
    private int maxWordLen;
    private int maxGuesses;
    private String hiddenWord;
    private int over;
    private char[] answer;
    private String input;

    public Hangman(WordsList words, int minWordLen, int maxWordLen, int maxGuesses) {
        this.words = words;
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
     * prepares game by getting a word and it's minimum and maximum length
     * @return the word that was picked and how long it is
     */
    @Override
    public String prepToPlay() {
        over = 0;
        hiddenWord = words.getWord(minWordLen, maxWordLen);
        answer = new char[hiddenWord.length()];
        for (int i = 0; i < hiddenWord.length(); i++) {
            answer[i] = '_';
        }
        return "I've picked a " + hiddenWord.length()
                + " letter word. Guess letters you think are in the word. You get " + maxGuesses + " guesses.";
    }

    /**Guesses whether the player has picked the right or wrong word
     *Also if the player has taken too many guesses
     * @return boolean True or False
     */
    @Override
    public boolean isOver() {
        if (over >= maxGuesses || new String(hiddenWord).equals(String.valueOf(answer))) {
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
            valid = false;
        } catch (NumberFormatException e) {
            if (move.length() == 1) {
                valid = true;
            } else {
                valid = false;
            }
        }
        return valid;
    }

    /**Checks whether th player has guessed the hidden word correctly
     *
     * @param move the input choice from the user
     * @return String message whether the player has won or lost
     */
    @Override
    public String processMove(String move) {
        // Creating array of string length
        char[] ch = new char[hiddenWord.length()];

        // Copy character by character into array
        for (int i = 0; i < hiddenWord.length(); i++) {
            ch[i] = hiddenWord.charAt(i);
        }

        over++;

        if (hiddenWord.contains(move)) {
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (ch[i] == move.charAt(0)) {
                    answer[i] = move.charAt(0);
                }
            }
        } else if (String.valueOf(answer) == hiddenWord) {
            return "That's it";
        }
        return String.valueOf(answer);
    }

    /**
     * Indicate what the hidden word is
     *
     * @return String message detailing what is the secrete word
     */
    @Override
    public String finalMessage() {
        return "The word was: " + hiddenWord;
    }
}
