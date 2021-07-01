package Risiko;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RisikoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;

	private GridLayout mainLayout;
	
	private JButton startButton;
	private JButton exitButton;
	
	private String startButtonName = "startBtn";
	private String exitButtonName = "exitBtn";
	
	
	 // Menü
	protected RisikoGUI()
	{
		setTitle("Risiko");
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(mainMenu());
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	
	}
	
	private JPanel mainMenu()
	{
		// Panel
		mainPanel = new JPanel();
		
		// Layout
		mainLayout = new GridLayout(2, 1, 0, 10);
		mainPanel.setLayout(mainLayout);
		
		// Buttons
		startButton = new JButton("Neues Spiel");
		exitButton = new JButton("Beenden");
		
		// Commands
		startButton.setActionCommand(startButtonName);
		exitButton.setActionCommand(exitButtonName);
		
		// Buttons hinzufügen
		mainPanel.add(startButton);
		mainPanel.add(exitButton);
		
		return mainPanel;
	}
	
	// Action listeners for riskView
	protected void risikoGUIActionListeners(ActionListener e)
	{
		startButton.addActionListener(e);
		exitButton.addActionListener(e);
	}
}

