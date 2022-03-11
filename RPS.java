import java.util.Random;

/**
 * The RPS class implements the common childhood game of “Rock Paper Scissors”.
 * Rock paper scissors is commonly played as a two-player game in which both
 * players are forced to reveal their move at the same time. The game of
 * rock-paper-scissors has three valid moves “rock”, “paper”, and “scissors” –
 * the goal of the game is to pick the move that beats your opponents choice.
 * Paper beats rock, rock beats scissors, and scissors beats paper. If two
 * players choose the same thing, they tie (neither player wins or looses) When
 * played with two players this game becomes an interesting game of psychology
 * in which you try to determine your opponents latent patterns and out-think
 * them to select their next move.
 */
public class RPS extends Game {
    private Random rng;
    private int requiredWins;
    private int maxLosses;
    private String[] choices;
    private String AIChoice;
    private int wins;
    private int losses;
    private String input;

    public RPS(Random rng, int requiredWins, int maxLosses) {
        this.rng = rng;
        this.requiredWins = requiredWins;
        this.maxLosses = maxLosses;
    }

    /**
     * Get game name
     *
     * @return the name of the class
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * Prepares the game for playing by initializing an array of choices and
     * assigning the game choices which are randomly chosen
     *
     * @return game instructions and win/lose conditions
     */
    @Override
    public String prepToPlay() {
        choices = new String[3];
        choices[0] = "paper";
        choices[1] = "rock";
        choices[2] = "scissor";
        AIChoice = choices[rng.nextInt(3)];
        wins = 0;
        losses = 0;
        return "Enter rock, paper, or scissors. Beat me " + maxLosses + " times before I win " + requiredWins
                + " times!";
    }

    /**
     * Checks whether the player the player has won enough rounds or lost too many
     * rounds
     *
     * @return boolean true or false
     */
    @Override
    public boolean isOver() {
        if (wins >= requiredWins || losses >= maxLosses) {
            return true;
        } else if (input == "quit") {
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
        boolean valid = false;
        input = move;

        if (new String(move).equals("rock") || new String(move).equals("paper")
                || new String(move).equals("scissors")) {
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    /**
     * Checks whether the player guess wins or loses or ties with the randomly game
     * selected choice
     *
     * @param move the input choice guess from the user
     * @return String message whether the player has won, loss or in a Tie
     */
    @Override
    public String processMove(String move) {
        AIChoice = choices[rng.nextInt(3)];
        if (new String("rock").equals(AIChoice) && new String("paper").equals(move)
                || new String("scissors").equals(AIChoice) && new String("rock").equals(move)) {
            wins++;
            return AIChoice + " vs. " + move + " you Win";
        } else if (new String("rock").equals(AIChoice) && new String("rock").equals(move)
                || new String("scissors").equals(AIChoice) && new String("scissors").equals(move)
                || new String("paper").equals(AIChoice) && new String("paper").equals(move)) {
            return AIChoice + " vs. " + move + " We Tie";
        } else {
            losses++;
            return AIChoice + " vs. " + move + " you lose";
        }
    }

    /**
     * Indicate if the player won or lost
     *
     * @return String message whether the player has won or lost the set
     */
    @Override
    public String finalMessage() {
        if (wins >= requiredWins) {
            return "You win the set";
        }
        return "You lose the set";
    }
}
