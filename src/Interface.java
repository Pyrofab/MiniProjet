package es.esy.ladysnake.gui;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

/**
 * A simple GUI used to display text messages from the application.
 * Sends CommandEntered events to the listeners. (objects that implements the CommandListener interface)
 */
public class Interface {

	private static Frame frame;
	private static List<CommandListener> listeners = new ArrayList<CommandListener>();

	/**
	 * Adds the object to the interface's listeners
	 * @param toAdd an implementation on the CommandListener interface
	 */
	public static void addCommandListener(CommandListener toAdd){
			listeners.add(toAdd);
	}

	/**
	 * Open the interface
	 * @param title the title this interface will display
	 * @param buttonTitle the confirmation button's displayed text
	 * @param labelText the hint displayed next to this interface's textField
	 */
	public static void openInterface(String title, String buttonTitle, String labelText) {
		frame = new Frame(title, buttonTitle, labelText);
	}

	/**
	 * Add a line to the log area
	 * @param s the String to display
	 */
	public static void addLogLine(String s) {
		if(frame != null) {
			String currText = frame.logArea.getText();
			frame.logArea.setText(currText.isEmpty() ? s : (currText + "\n" + s));
		}
	}

	/**
	 * Clear the log area
	 */
	public static void clear(){
		if(frame != null) {
			frame.logArea.setText("");
		}
	}

	/**
	 * Changes the status displayed at the bottom of the interface
	 * @param status the status to display
	 */
	public static void setStatus(String status) {
		if(frame != null)
			frame.currentStatus.setText(status);
	}

	/**
	 * Swaps the Interface's panel with the default one
	 */
	public static void swapPanel() {
		frame.setContentPane(frame.panel);
	}

	/**
	 * Swaps the Interface's panel with the one provided in the parameters
	 * @param newPanel the JPanel for this frame
	 */
	public static void swapPanel(JPanel newPanel) {
		frame.setContentPane(newPanel);
	}

	static final class Frame extends JFrame implements ActionListener, KeyListener {

		private static final long serialVersionUID = -2280547253170432552L;
		private ArrayList<String> journal;
		private int indiceJournal;

		JPanel panel;
		JButton confirmButton;
		JLabel label;
		JScrollPane scrollPane;
		JTextField inputField;
		JTextArea logArea;
		JLabel currentStatus;

		public Frame(String title, String buttonTitle, String labelText) {
			setSize(800, 640);
			setTitle(title);

			journal = new ArrayList<String>();
			indiceJournal = 0;

			panel = new JPanel();
			confirmButton = new JButton(buttonTitle);
			label = new JLabel(labelText);
			inputField = new JTextField("", 42);

			logArea = new JTextArea(34, 68);
			logArea.setBackground(Color.WHITE);
			logArea.setEditable(false);
			logArea.setLineWrap(true);
			scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			DefaultCaret caret = (DefaultCaret) logArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

			currentStatus = new JLabel("Idle", SwingConstants.LEFT);

			panel.add(label);
			panel.add(inputField);
			panel.add(confirmButton);
			panel.add(scrollPane);
			panel.add(currentStatus);
			add(panel);

			confirmButton.requestFocus();
			confirmButton.addActionListener(this);
			inputField.addKeyListener(this);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(1);
				}
			});

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			setResizable(false);
			setVisible(true);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n')
				actionPerformed(null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = inputField.getText();
			if(s.length() <= 0) return;
			ArrayList<String> commande = new ArrayList<String>();
			journal.add(s);
			Scanner sc = new Scanner(s);
			try {
				while(sc.hasNext()) {
					commande.add(sc.next().trim());
				}
			} catch(Exception ex) {}
			for(CommandListener l1 : listeners)
				l1.commandEntered(commande);
			inputField.setText("");
			indiceJournal = 0;
			sc.close();
		}

		@Override
		public void keyPressed(KeyEvent event){
			if(event.getKeyCode() == 38){
				try{
					this.inputField.setText(this.journal.get(this.journal.size() - ++this.indiceJournal));
				} catch (IndexOutOfBoundsException e) {
					--this.indiceJournal;
				}
			}
			if(event.getKeyCode() == 40){
				try{
					this.inputField.setText(this.journal.get(this.journal.size() - --this.indiceJournal));
				} catch (IndexOutOfBoundsException e) {
					this.inputField.setText("");
					if(this.indiceJournal < 0){
						this.indiceJournal = 0;
					}
				}
			}
			// System.out.println(event.getKeyCode());
		}
		@Override public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == 17)
				inputField.setText(listeners.get(0).commandTabbed(inputField.getText()));
		}
	}

}
