package Risiko;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
		// ActionListeners für GUI
	protected void risikoGUIActionListeners(ActionListener e)
	{
		startButton.addActionListener(e);
		exitButton.addActionListener(e);
	}
}

class SpielerAnzahl extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel spielerAnzahlPanel;
	private GridLayout spielerAnzahlLayout;
	private JLabel spielerAnzahlLabel;
	
	private JButton dreiSpielerBtn;
	private JButton vierSpielerBtn;
	private JButton fuenfSpielerBtn;
	private JButton zurueckBtn;
	
	private String dreiSpielerBtnName = "dreiSpielerBtn";
	private String vierSpielerBtnName = "vierSpielerBtn";
	private String fuenfSpielerBtnName = "fuenfSpielerBtn";
	private String zurueckBtnName = "zurueckBtn";
	
	protected SpielerAnzahl(RisikoGUI bla, boolean blub) 
	{
		super(bla, blub);
		setTitle("Risiko");
		setPreferredSize(new Dimension(150, 300));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		add(spielerAnzahlPanel());
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel spielerAnzahlPanel()
	{
		spielerAnzahlPanel = new JPanel();
		
		spielerAnzahlLayout = new GridLayout(5, 1, 5, 5);
		spielerAnzahlPanel.setLayout(spielerAnzahlLayout);
		
		spielerAnzahlLabel = new JLabel("Anzahl der Spieler:");
		
		dreiSpielerBtn = new JButton("Drei");
		vierSpielerBtn = new JButton("Vier");
		fuenfSpielerBtn = new JButton("Fünf");
		zurueckBtn = new JButton("Zurück");
		
		dreiSpielerBtn.setActionCommand(dreiSpielerBtnName);
		vierSpielerBtn.setActionCommand(vierSpielerBtnName);
		fuenfSpielerBtn.setActionCommand(fuenfSpielerBtnName);
		zurueckBtn.setActionCommand(zurueckBtnName);
		
		spielerAnzahlPanel.add(spielerAnzahlLabel);
		spielerAnzahlPanel.add(dreiSpielerBtn);
		spielerAnzahlPanel.add(vierSpielerBtn);
		spielerAnzahlPanel.add(fuenfSpielerBtn);
		spielerAnzahlPanel.add(zurueckBtn);
		
		return spielerAnzahlPanel;
	}
	
	protected void addActionListeners(ActionListener e)
	{
		dreiSpielerBtn.addActionListener(e);
		vierSpielerBtn.addActionListener(e);
		fuenfSpielerBtn.addActionListener(e);
		zurueckBtn.addActionListener(e);
	}
}

