package Risiko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;

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
		setPreferredSize(new Dimension(300, 400));
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
	
	protected SpielerAnzahl(RisikoGUI bla, boolean blubb) 
	{
		super(bla, blubb);
		setTitle("Risiko");
		setPreferredSize(new Dimension(300, 400));
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

class SpielereinstellungGUI extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private JPanel spielerNamePanel;
	
	private GridLayout mainLayout;
	private GridLayout spielerLayout;
	
	private JButton startBtn;
	private JButton zurueckBtn;
	
	private String startBtnName = "startBtn";
	private String zurueckBtnName = "zurueckBtn";
	
	private JTextField spieler1TextField;
	private JTextField spieler2TextField;
	private JTextField spieler3TextField;
	private JTextField spieler4TextField;
	private JTextField spieler5TextField;
	
	private int spielerZahl;
	
	public SpielereinstellungGUI(SpielerAnzahl bla, boolean blubb, int spielerZahl)
	{
		super(bla,blubb);
		setTitle("Risiko");
		setPreferredSize(new Dimension(300, 400));
		
		this.spielerZahl = spielerZahl;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		mainLayout = new GridLayout(1, 1, 5, 5);
		setLayout(mainLayout);
		
		add(spielerNamePanel());
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel spielerNamePanel()
	{
		spielerNamePanel = new JPanel();
		
		spielerNamePanel.setPreferredSize(new Dimension(400, spielerZahl * 80 + 40));
		
		spielerLayout = new GridLayout(spielerZahl + 2, 1, 5, 5);
		spielerNamePanel.setLayout(spielerLayout);
		
		spieler1TextField = new JTextField("Peter");
		spieler1TextField.setBackground(Color.BLUE);
		spieler2TextField = new JTextField("Hans");
		spieler2TextField.setBackground(Color.RED);
		spieler3TextField = new JTextField("Werner");
		spieler3TextField.setBackground(Color.YELLOW);
		
		spielerNamePanel.add(spieler1TextField);
		spielerNamePanel.add(spieler2TextField);
		spielerNamePanel.add(spieler3TextField);
		
		if (spielerZahl == 4)
		{
			spieler4TextField = new JTextField("Joachim");
			spieler4TextField.setBackground(Color.GREEN);
			spielerNamePanel.add(spieler4TextField);
		}
		if (spielerZahl == 5)
		{
			spieler4TextField = new JTextField("Joachim");
			spieler4TextField.setBackground(Color.GREEN);
			spieler5TextField = new JTextField("Klaus");
			spieler5TextField.setBackground(Color.WHITE);
			spielerNamePanel.add(spieler4TextField);
			spielerNamePanel.add(spieler5TextField);
		}
		
		zurueckBtn = new JButton("Zurück");
		zurueckBtn.setActionCommand(zurueckBtnName);
		spielerNamePanel.add(zurueckBtn);
		startBtn = new JButton("Starte Spiel");
		startBtn.setActionCommand(startBtnName);
		spielerNamePanel.add(startBtn);
		
		return spielerNamePanel;
	}
	
	public void addActionListeners(ActionListener e)
	{
		startBtn.addActionListener(e);
		zurueckBtn.addActionListener(e);
	}
	
	protected String getSpielerTextField(int spielerNummer)
	{
		if(spielerNummer == 1)
		{
			return spieler1TextField.getText();
		}
		else if(spielerNummer == 2)
		{
			return spieler2TextField.getText();
		}
		else if(spielerNummer == 3)
		{
			return spieler3TextField.getText();
		}
		else if(spielerNummer == 4)
		{
			return spieler4TextField.getText();
		}
		else 
		{
			return spieler5TextField.getText();
		}
	}
}

class Spielbrett extends JDialog {
	
	private static final long serialVersionUID = 1L;
		
	public Spielbrett() {
		
		  Runnable r = () -> {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            Weltkarte o = new Weltkarte();

	            JFrame f = new JFrame(o.getClass().getSimpleName());
	            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            f.setContentPane(o.getUI());
	            f.setResizable(false);
	            f.pack();
	            f.setSize(850, 590);
	            f.setLocationRelativeTo(null);
	            f.setVisible(true);
	            f.setTitle("Risiko");
	        };
	        SwingUtilities.invokeLater(r);
		
	}
}
