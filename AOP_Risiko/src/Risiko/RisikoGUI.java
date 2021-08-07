package Risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
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
	
	private JButton startBtn;
	private JButton zurueckBtn;
	
	private String startBtnName = "startBtn";
	private String zurueckBtnName = "zurueckBtn";
	
	private JTextField spieler1TextField;
	private JTextField spieler2TextField;
	private JTextField spieler3TextField;
	private JTextField spieler4TextField;
	private JTextField spieler5TextField;
	
	private Component emptySpace = Box.createRigidArea(new Dimension(0,90));
	
	private int spielerZahl;
	
	public SpielereinstellungGUI(SpielerAnzahl bla, boolean blubb, int spielerZahl)
	{
		super(bla,blubb);
		setTitle("Risiko");
		setPreferredSize(new Dimension(300, 400));
		
		this.spielerZahl = spielerZahl;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
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
		spieler1TextField.setForeground(Color.white);
		
		spieler2TextField = new JTextFieldDesign(15);
		spieler2TextField.setBackground(new Color(0xcc0000));
		spieler2TextField.setForeground(Color.white);
		
		spieler3TextField = new JTextFieldDesign(15);
		spieler3TextField.setBackground(new Color(0xffff00));
		
		spielerNamePanel.add(spieler1TextField);
		spielerNamePanel.add(spieler2TextField);
		spielerNamePanel.add(spieler3TextField);
		spielerNamePanel.add(emptySpace);
		
		if (spielerZahl == 4)
		{
			spielerNamePanel.remove(emptySpace);
			emptySpace = Box.createRigidArea(new Dimension(0,70));
			spieler4TextField = new JTextFieldDesign(15);
			spieler4TextField.setBackground(new Color(0x2dce98));
			spielerNamePanel.add(spieler4TextField);
			spielerNamePanel.add(emptySpace);
		}
		if (spielerZahl == 5)
		{
			spielerNamePanel.remove(emptySpace);
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

class Gebietskarten extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel menuPanel;
	private JPanel trpPanel;
	
	private JButton einloesenButton;
	private JButton schliessenButton;
	
	private String einloesenBtnName = "einloesenBtnName";
	private String schliessenBtnName = "schliessenBtnName";
	
	private JLabel iLabel;
	private JLabel kLabel;
	private JLabel aLabel;
	private JLabel i2Label;
	private JLabel k2Label;
	private JLabel a2Label;
	
	private Image image1;
    private Image newimage1;
    private Image image2;
    private Image newimage2;
    private Image image3;
    private Image newimage3;
    
    private ImageIcon infanterie;
    private ImageIcon kavallerie;
    private ImageIcon artillerie;
	
	public Gebietskarten() {
		setTitle("Risiko");
		setPreferredSize(new Dimension(300,300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(Menu());
		
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private JPanel Menu() {
		
		menuPanel = new JPanel();
		
		einloesenButton = new JButton("Einloesen");
		einloesenButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		einloesenButton.setBackground(new Color(0x2dce98));
		einloesenButton.setForeground(Color.white);
		einloesenButton.setUI(new ButtonDesign());
		einloesenButton.setActionCommand(einloesenBtnName);
		
		schliessenButton = new JButton("Schließen");
		schliessenButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		schliessenButton.setBackground(new Color(0x2dce98));
		schliessenButton.setForeground(Color.white);
		schliessenButton.setUI(new ButtonDesign());
		schliessenButton.setActionCommand(schliessenBtnName);

		infanterie = new ImageIcon("src\\img\\Infanterie.png");
	    image1 = infanterie.getImage();
	    newimage1 = image1.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
	    infanterie = new ImageIcon(newimage1);

	    kavallerie = new ImageIcon("src\\img\\Kavallerie.png");
	    image2 = kavallerie.getImage();
	    newimage2 = image2.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
	    kavallerie = new ImageIcon(newimage2);

	    artillerie = new ImageIcon("src\\img\\Artillerie.png");
	    image3 = artillerie.getImage();
	    newimage3 = image3.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);
	    artillerie = new ImageIcon(newimage3);
	    
	    iLabel = new JLabel();
	    iLabel.setIcon(infanterie);
        
	    kLabel = new JLabel();
	    kLabel.setIcon(kavallerie);
        
	    aLabel = new JLabel();
	    aLabel.setIcon(artillerie);
	    
	    i2Label= new JLabel("5x");
	    i2Label.setFont(new Font("Calibri",Font.PLAIN,25));
	    k2Label= new JLabel("3x");
	    k2Label.setFont(new Font("Calibri",Font.PLAIN,25));
	    a2Label= new JLabel("2x");
	    a2Label.setFont(new Font("Calibri",Font.PLAIN,25));

	    trpPanel = new JPanel();
		trpPanel.setPreferredSize(new Dimension(250,30));
		trpPanel.setLayout(new BorderLayout(60,0));
		trpPanel.setBorder(new EmptyBorder(0,25,0,30));
		trpPanel.add(i2Label,BorderLayout.WEST);
		trpPanel.add(k2Label,BorderLayout.CENTER);
		trpPanel.add(a2Label,BorderLayout.EAST);
		
		menuPanel.add(Box.createRigidArea(new Dimension(250,20)));
	    menuPanel.add(iLabel);
	    menuPanel.add(kLabel);
	    menuPanel.add(aLabel);
	    menuPanel.add(trpPanel);
	    menuPanel.add(Box.createRigidArea(new Dimension(250,20)));
		menuPanel.add(einloesenButton);
		menuPanel.add(schliessenButton);
		
		GebietskartenListeners(this);
		
		return menuPanel;
	}
	
	 protected void GebietskartenListeners(ActionListener e) 
	    {
		 	einloesenButton.addActionListener(e);
	    	schliessenButton.addActionListener(e);
	    }

	@Override
	public void actionPerformed(ActionEvent e) {

		String actionEvent = e.getActionCommand();
		
		if(actionEvent.equals("einloesenBtnName"))
		{
			
		}
		
		if(actionEvent.equals("schliessenBtnName"))
		{
			setVisible(false);
		}
		
	}
}
class Spielbrett extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static String stext;
		
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
	            f.setSize(850, 700);
	            f.getContentPane().setBackground(new Color(0x3f47cc));
	            f.setLocationRelativeTo(null);
	            f.setVisible(true);
	            f.setTitle("Risiko");
	        };
	        SwingUtilities.invokeLater(r);
		
	}
	
	public static void setText(String text) {
		stext = text;
	}
	
	public static String getText() {
		return stext;
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

class TruppenButtonDesign extends BasicButtonUI {
	
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
         g.setColor(Color.BLACK);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
}

