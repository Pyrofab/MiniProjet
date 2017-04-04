package es.esy.ladysnake.gui;
import java.util.ArrayList;


/**
 * The listener interface for receiving command events (from the interface).
 * The class that is interested in processing a command event either implements this interface (and all the methods it contains)
 */
public interface CommandListener {
	/**
	 * Invoked when a command has been entered.
	 * @param commande The command that's been input along with its arguments.
	 */
	abstract void commandEntered(ArrayList<String> commande);
	/**
	 * Invoked when the user tries to autocomplete the command he's typing.
	 * @param commande The command that's been written so far.
	 * @return The command the implementation thinks has been asked for.
	 */
	abstract String commandTabbed(String commande);
}
