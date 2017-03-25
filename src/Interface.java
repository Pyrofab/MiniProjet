package es.esy.ladysnake.gui;

import java.util.List;
import java.util.ArrayList;

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

public class Interface {

	private static Frame frame;
	private static List<CommandListener> listeners = new ArrayList<CommandListener>();
	
	/**
	 * Adds the object to the interface's listeners
	 */
	public static void addCommandListener(CommandListener toAdd){
			listeners.add(toAdd);
	}
	
	/**
	 * Open the interface
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

	static final class Frame extends JFrame implements ActionListener, KeyListener {

		private static final long serialVersionUID = -2280547253170432552L;

		JPanel panel;
		JButton confirmButton;
		JLabel label;
		JScrollPane scrollPane;
		JTextField commandField;
		JTextArea logArea;
		JLabel currentStatus;

		public Frame(String title, String buttonTitle, String labelText) {
			setSize(800, 640);
			setTitle(title);

			panel = new JPanel();
			confirmButton = new JButton(buttonTitle);
			label = new JLabel(labelText);
			commandField = new JTextField("", 54);

			logArea = new JTextArea(34, 68);
			logArea.setBackground(Color.WHITE);
			logArea.setEditable(false);
			logArea.setLineWrap(true);
			scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			DefaultCaret caret = (DefaultCaret) logArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

			currentStatus = new JLabel("Idle", SwingConstants.LEFT);

			panel.add(label);
			panel.add(commandField);
			panel.add(confirmButton);
			panel.add(scrollPane);
			panel.add(currentStatus);
			add(panel);

			confirmButton.requestFocus();
			confirmButton.addActionListener(this);
			commandField.addKeyListener(this);

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
			String s = commandField.getText();
			if(s.isEmpty() || s.indexOf(';') < 0)
				return;
			ArrayList<String> commande = new ArrayList<String>();
			int index = 0, argIndex = 0;
			do {
				argIndex = index;
				index = s.indexOf(';', index) + 1;
				while(s.indexOf(' ', argIndex) < index && s.indexOf(' ', argIndex) >= 0){
					commande.add(s.substring(argIndex, s.indexOf(' ', argIndex)));
					argIndex = s.indexOf(' ', argIndex) + 1;
				}
				commande.add(s.substring(argIndex, index - 1));
				for(CommandListener l1 : listeners)
					l1.commandEntered(commande);
			} while (index < s.lastIndexOf(';'));
			commandField.setText("");
		}

		@Override public void keyPressed(KeyEvent e) {  }
		@Override public void keyReleased(KeyEvent e) { }
	}

}
