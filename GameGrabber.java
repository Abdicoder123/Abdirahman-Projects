
import java.util.Random;
import java.util.Scanner;

/** This class grabs games and presents a menu for the user to interact with
 * and play the games
 */


public class GameGrabber {
    private Game[] gamesList;
    private Scanner userScanner;
    private int gameSelected;


    public GameGrabber(Game[] games, Scanner user) {
        this.gamesList = games;
        this.userScanner = user;
    }

    /**
     * Shows the menu for the class for different games
     */
    public void doMenu() {
        boolean gameOn = true;
        while (gameOn == true) {
            int count = 0;
            for (Game game : gamesList) {
                System.out.println(count + ") " + game.getName());
                count++;
            }
            System.out.println(count + ") Quit");

            System.out.print("Pick a game (0-" + count + ") ");
            gameSelected = Integer.parseInt(userScanner.next());

            while (gameSelected < 0 || gameSelected > gamesList.length) {
                System.out.print("Pick a game (0-" + count + ") ");
                gameSelected = Integer.parseInt(userScanner.next());
            }
            if (gameSelected == gamesList.length) {
                System.out.println("goodbye");
                // System.exit(0);
                return;
            } else {
                gamesList[gameSelected].play(userScanner);
            }
        }
    }

    /**
     * Calls on the Game classes that were created and states the rules
     * @param gamesList[] takes in the parameters we want the game to play in
     */
    public static void main(String[] args) {
        // Creating an array of books
        Game[] gamesList = new Game[4];
        Random rand = new Random();
        WordsList words = new WordsList(rand);

        gamesList[0] = new Hangman(words, 3, 8, 10);
        gamesList[1] = new NumberGuesser(rand, 1000, 10);
        gamesList[2] = new RPS(rand, 4, 4);
        gamesList[3] = new WordJumble(words, rand, 3, 6, 10);

        // Creating an instance of a scanner
        Scanner user = new Scanner(System.in);

        // instance of grabber
        GameGrabber g = new GameGrabber(gamesList, user);
        g.doMenu();
    }
}
