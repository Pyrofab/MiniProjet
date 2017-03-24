package es.esy.ladysnake.gui;

import es.esy.ladysnake.miniprojet.MiniProjet;
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

	public static void openInterface(String title, String buttonTitle, String labelText) {
		frame = new Frame(title, buttonTitle, labelText);
	}

	public static void addLogLine(String s) {
		if(frame != null) {
			String currText = frame.logArea.getText();
			frame.logArea.setText(currText.isEmpty() ? s : (currText + "\n" + s));
		}
	}

	public static void setStatus(String status) {
		if(frame != null)
			frame.currentStatus.setText(status);
	}

	static final class Frame extends JFrame implements ActionListener, KeyListener {

		private static final long serialVersionUID = -2280547253170432552L;
		private static List<CommandListener> listeners = new ArrayList<CommandListener>();

		public void addListener(CommandListener toAdd){
			listeners.add(toAdd);
		}

		JPanel panel;
		JButton downloadButton;
		JLabel label;
		JScrollPane scrollPane;
		JTextField urlField;
		JTextArea logArea;
		JLabel currentStatus;

		public Frame(String title, String buttonTitle, String labelText) {
			setSize(800, 640);
			setTitle(title);

			panel = new JPanel();
			downloadButton = new JButton(buttonTitle);
			label = new JLabel(labelText);
			urlField = new JTextField("", 54);

			logArea = new JTextArea(34, 68);
			logArea.setBackground(Color.WHITE);
			logArea.setEditable(false);
			logArea.setLineWrap(true);
			scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			DefaultCaret caret = (DefaultCaret) logArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

			currentStatus = new JLabel("Idle", SwingConstants.LEFT);

			panel.add(label);
			panel.add(urlField);
			panel.add(downloadButton);
			panel.add(scrollPane);
			panel.add(currentStatus);
			add(panel);

			downloadButton.requestFocus();
			downloadButton.addActionListener(this);
			urlField.addKeyListener(this);

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
			String s = urlField.getText();
			ArrayList<String> commande = new ArrayList<String>();
	    int index = 0, argIndex = 0;
	    //do {
				commande.clear();
				argIndex = s.indexOf(' ', index);
				if(argIndex > 0){
					commande.set(0, s.substring(index, argIndex));
		      index = s.indexOf(';', index+1);
					do {
						commande.add(s.substring(s.indexOf(' ', argIndex)));
						argIndex = s.indexOf(' ', argIndex+1);
					} while(argIndex < index);
				} else commande.set(0, s.substring(0, s.indexOf(';')));
				for(CommandListener l1 : listeners)
					l1.commandEntered((String[])commande.toArray());
	    //} while(index < s.lastIndexOf(';'));
			urlField.setText("");
		}

		@Override public void keyPressed(KeyEvent e) {  }
		@Override public void keyReleased(KeyEvent e) { }
	}

}

interface CommandListener {
	void commandEntered(String[] commande);
}
