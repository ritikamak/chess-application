import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class Chess
{
	public static Game game;
	public static ChessGUI gui;
	public static ArrayList<String> fenList; //list of player moves;
	public static GameScribe gamescribe;

	public static void newGame()
	{
		game = new Game();
		game.setup();
		gamescribe = new GameScribe(game);
		gui.display(false);
		gui.dispose();
		gui = new ChessGUI(game);
		gui.display(true);
		InputHandler.initInputHandler(game, gui, gamescribe);
	}

	public static void loadGame()
	{
		try{
			ArrayList<String> loadGame = new ArrayList<String>();
			Scanner k = new Scanner(System.in);
			System.out.println("Please enter a name for the input file: ");
			String f = k.next();
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while(reader.ready()){
				loadGame.add(reader.readLine());
			}
			reader.close();
			k.close();
		
			//start a new game and send this string array list off to game scribe to do the dirty werk
			newGame();
			gamescribe.takeALoadOff(loadGame);
			//refresh display and we should be saul good man
			gui.refreshBoard();
			gui.updateSidePanel(game.getPlayer(true), game.getPlayer(false));
			System.out.println("Game successfully loaded. Good luck!");
          }
          catch (Exception e){
			  System.out.println("Sorry, Friendo! We couldn't find a game by that name! (hey?! that rhymes!)");
          }
	}
	
	public static void main(String[] args)
	{
		fenList = new ArrayList<String>();
		Kibitzer kib = new Kibitzer();
		kib.start();
		game = new Game();
		game.setup();
		gamescribe = new GameScribe(game);
		gui = new ChessGUI(game);
		InputHandler.initInputHandler(game, gui, gamescribe);
		gui.display(true);
	}
}
