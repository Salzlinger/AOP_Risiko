package Risiko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class RisikoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;

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
		mainPanel.setBackground(new Color(0xcccccc));
		
		// Layout
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setBorder(new EmptyBorder(100, 80, 40, 80));
		
		// Buttons
		startButton = new JButton("Starte Spiel");
		startButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		startButton.setBackground(new Color(0x2dce98));
		startButton.setForeground(Color.white);
		startButton.setUI(new ButtonDesign());
		
		exitButton = new JButton("Beenden");
		exitButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		exitButton.setBackground(new Color(0x2dce98));
		exitButton.setForeground(Color.white);
		exitButton.setUI(new ButtonDesign());
		
		// Commands
		startButton.setActionCommand(startButtonName);
		exitButton.setActionCommand(exitButtonName);
		
		// Buttons hinzufügen
		mainPanel.add(startButton);
		mainPanel.add(Box.createRigidArea(new Dimension(50, 20)));
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
		spielerAnzahlPanel.setBackground(new Color(0xcccccc));
		
		// Layout
		spielerAnzahlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		spielerAnzahlPanel.setBorder(new EmptyBorder(20, 80, 40, 80));
		
		spielerAnzahlLabel = new JLabel("Anzahl der Spieler:");
		spielerAnzahlLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		
		dreiSpielerBtn = new JButton("Drei");
		dreiSpielerBtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		dreiSpielerBtn.setBackground(new Color(0x2dce98));
		dreiSpielerBtn.setForeground(Color.white);
		dreiSpielerBtn.setUI(new ButtonDesign());
		
		vierSpielerBtn = new JButton("Vier");
		vierSpielerBtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		vierSpielerBtn.setBackground(new Color(0x2dce98));
		vierSpielerBtn.setForeground(Color.white);
		vierSpielerBtn.setUI(new ButtonDesign());
		
		fuenfSpielerBtn = new JButton("Fünf");
		fuenfSpielerBtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		fuenfSpielerBtn.setBackground(new Color(0x2dce98));
		fuenfSpielerBtn.setForeground(Color.white);
		fuenfSpielerBtn.setUI(new ButtonDesign());
		
	  	zurueckBtn = new JButton("Zurück");
		zurueckBtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		zurueckBtn.setBackground(new Color(0x2dce98));
		zurueckBtn.setForeground(Color.white);
		zurueckBtn.setUI(new ButtonDesign());
		
		dreiSpielerBtn.setActionCommand(dreiSpielerBtnName);
		vierSpielerBtn.setActionCommand(vierSpielerBtnName);
		fuenfSpielerBtn.setActionCommand(fuenfSpielerBtnName);
		zurueckBtn.setActionCommand(zurueckBtnName);
		
		spielerAnzahlPanel.add(spielerAnzahlLabel);
		spielerAnzahlPanel.add(Box.createRigidArea(new Dimension(50, 20)));
		spielerAnzahlPanel.add(dreiSpielerBtn);
		spielerAnzahlPanel.add(vierSpielerBtn);
		spielerAnzahlPanel.add(fuenfSpielerBtn);
		spielerAnzahlPanel.add(Box.createRigidArea(new Dimension(50, 40)));
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
		
//		mainLayout = new GridLayout(1, 1, 5, 5);
	//	setLayout(mainLayout);
		
		add(spielerNamePanel());
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel spielerNamePanel()
	{
		spielerNamePanel = new JPanel();
		spielerNamePanel.setBackground(new Color(0xcccccc));
		
		spielerNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		spielerNamePanel.setBorder(new EmptyBorder(20, 80, 10, 80));
		
		spieler1TextField = new JTextFieldDesign(15);
		spieler1TextField.setBackground(new Color(0x0b5394));
		
		spieler2TextField = new JTextFieldDesign(15);
		spieler2TextField.setBackground(new Color(0xcc0000));
		
		spieler3TextField = new JTextFieldDesign(15);
		spieler3TextField.setBackground(new Color(0xffff00));
		
		spielerNamePanel.add(spieler1TextField);
		spielerNamePanel.add(spieler2TextField);
		spielerNamePanel.add(spieler3TextField);
		
		if (spielerZahl == 4)
		{
			spieler4TextField = new JTextFieldDesign(15);
			spieler4TextField.setBackground(new Color(0x2dce98));
			spielerNamePanel.add(spieler4TextField);
		}
		if (spielerZahl == 5)
		{
			spieler4TextField = new JTextFieldDesign(15);		
			spieler4TextField.setBackground(new Color(0x2dce98));
			spieler5TextField = new JTextFieldDesign(15);
			spieler5TextField.setBackground(new Color(0xffffff));
			
			spielerNamePanel.add(spieler4TextField);
			spielerNamePanel.add(spieler5TextField);
		}
		
		spielerNamePanel.add(Box.createRigidArea(new Dimension(50, 40)));
		
		zurueckBtn = new JButton("Zurück");
		zurueckBtn.setFont(new Font("Calibri", Font.PLAIN, 20));
		zurueckBtn.setBackground(new Color(0x2dce98));
		zurueckBtn.setForeground(Color.white);
		zurueckBtn.setUI(new ButtonDesign());
		zurueckBtn.setActionCommand(zurueckBtnName);
		
		startBtn = new JButton("Starte Spiel");
		startBtn.setFont(new Font("Calibri", Font.PLAIN, 30));
		startBtn.setBackground(new Color(0x2dce98));
		startBtn.setForeground(Color.white);
		startBtn.setUI(new ButtonDesign());
		startBtn.setActionCommand(startBtnName);
		
		spielerNamePanel.add(startBtn);
		spielerNamePanel.add(Box.createRigidArea(new Dimension(50, 40)));
		spielerNamePanel.add(zurueckBtn);
		
		
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
	            f.setSize(850, 600);
	            f.getContentPane().setBackground(new Color(0x3f47cc));
	            f.setLocationRelativeTo(null);
	            f.setVisible(true);
	            f.setTitle("Risiko");
	        };
	        SwingUtilities.invokeLater(r);
		
	}
}
class ButtonDesign extends BasicButtonUI {
	
	@Override
	public void installUI (JComponent c) {
		super.installUI(c);
		AbstractButton button = (AbstractButton) c;
		button.setOpaque(false);
		button.setBorder(new EmptyBorder(5, 15, 5, 15));
	}
	
	@Override
	public void paint (Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		paintBackground(g, b, b.getModel().isPressed() ? 2: 0);
		super.paint(g, c);
	}
	
	private void paintBackground(Graphics g, JComponent c, int yOffset) {
		 Dimension size = c.getSize();
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setColor(c.getBackground().darker());
	        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
	        g.setColor(c.getBackground());
	        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
	}
}

class JTextFieldDesign extends JTextField {

	private static final long serialVersionUID = 1L;
	private Shape shape;
    public JTextFieldDesign(int size) {
        super(size);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
}
