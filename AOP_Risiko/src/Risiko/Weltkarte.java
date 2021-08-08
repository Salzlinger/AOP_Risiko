package Risiko;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;

public class Weltkarte implements ActionListener {

    private JComponent ui = null;
    
    private JLabel output;
    static 	JLabel phase;
    static JLabel w1;
    static JLabel w2;
    static JLabel w3;
    static JLabel w4;
    static JLabel w5;
    static JLabel w6;
    private static JLabel trpLabel;

    private JPanel btnPanel;
    private JPanel spielerPanel;
    private JPanel waPanel;
    private JPanel wvPanel;
    private static JPanel trpPanel;
    private static JPanel zwPanel;

    private JButton truppenBtn;
    private static JButton nextBtn;
    private static JButton hochBtn;
    private static JButton runterBtn;
    private static JButton normalBtn;
    private static JButton blitzBtn;
    private static JButton angriffBtn;
    private static JButton verschBtn;

    private String truppenBtnName = "truppenBtn";
    private String nextBtnName = "nextBtn";
    private String hochBtnName = "hochBtn";
    private String runterBtnName = "runterBtn";
    private String normalBtnName = "normalBtn";
    private String blitzBtnName = "blitzBtn";
    private String angriffBtnName = "angriffBtn";
    private String verschBtnName = "verschBtn";

    private Gebietskarten gebietskarten;

    public static final int SIZE = 550;
    private BufferedImage bild;
    private Area area;
    static ArrayList<Shape> shapeList;
    private static int pointer;
    private static int z = 0;
	private static int a = 0;
	private static Laender start;
	private static Laender ziel;
	private static Boolean erstercyclus = true;
	private static int truppen = 1;
	private static int si = 0;
	private static Boolean postBattle = false;
	private static Boolean cont = false;
	private static int max = 1;
	private static Boolean blitz = false;
	private static Boolean normal = false;
	private static Spieler player0 = Spieler.spieler.get(0);

	public static Boolean getblitz() {
		return blitz;
	}

	public static Boolean getnormal() {
		return normal;
	}

	public static int getTruppen() {
		return truppen;
	}

    public static int getPointer()
    {
		return pointer;
	}

	public Weltkarte()
	{
        try {
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public final void initUI() throws Exception
    {
        if (ui != null) {
            return;
        }

        //Laden der Wuerfelbilder

        String imagePath = "src\\img\\risk.png";
        bild = ImageIO.read(new File(imagePath));

        area = getOutline(Color.WHITE, bild, 60);

        shapeList = separateShapeIntoRegions(area);
        ui = new JPanel(new BorderLayout(4, 0));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));

        output = new JLabel();
        output.addMouseMotionListener(new MousePositionListener());
        output.addMouseListener(new MouseClickListener());

        btnPanel = new JPanel();
        btnPanel.setBackground(new Color(0x3f47cc));
        btnPanel.setLayout(new BorderLayout(10,0));
        btnPanel.setBorder(new EmptyBorder(10,10,10,10));
        btnPanel.setPreferredSize(new Dimension(0,100));

        waPanel = new JPanel();
        waPanel.setLayout(new GridLayout(0,3));
        waPanel.setBackground(new Color(0x3f47cc));
        waPanel.setVisible(false);

        wvPanel = new JPanel();
        wvPanel.setLayout(new GridLayout(0,3));
        wvPanel.setBackground(new Color(0x3f47cc));
        wvPanel.setVisible(false);

        zwPanel = new JPanel();
        zwPanel.setLayout(new BorderLayout(0,0));
        zwPanel.setVisible(false);

        spielerPanel = new JPanel();
        spielerPanel.setBackground(new Color(0x3f47cc));
        spielerPanel.setLayout(new BorderLayout(0,30));
        spielerPanel.setPreferredSize(new Dimension(0,20));

        trpPanel = new JPanel(new GridBagLayout());
        trpPanel.setBackground(new Color(0x3f47cc));

        GridBagConstraints c = new GridBagConstraints();

        truppenBtn = new JButton("Gebietskarten");
        truppenBtn.setFont(new Font("Calibri", Font.PLAIN,20));
        truppenBtn.setBackground(new Color(0x2dce98));
        truppenBtn.setForeground(Color.white);
        truppenBtn.setUI(new ButtonDesign());
        truppenBtn.setActionCommand(truppenBtnName);

        nextBtn = new JButton("");
        nextBtn.setFont(new Font("Calibri", Font.PLAIN,20));
        nextBtn.setPreferredSize(new Dimension(150,0));
        nextBtn.setBackground(new Color(0x2dce98));
        nextBtn.setForeground(Color.white);
        nextBtn.setUI(new ButtonDesign());
        nextBtn.setActionCommand(nextBtnName);
        
        angriffBtn = new JButton("Angriff");
        angriffBtn.setFont(new Font("Calibri", Font.PLAIN,20));
        angriffBtn.setPreferredSize(new Dimension(150,0));
        angriffBtn.setBackground(new Color(0x2dce98));
        angriffBtn.setForeground(Color.white);
        angriffBtn.setUI(new ButtonDesign());
        angriffBtn.setActionCommand(nextBtnName);
        angriffBtn.setVisible(false);
        
        verschBtn = new JButton("Verschieben");
        verschBtn.setFont(new Font("Calibri", Font.PLAIN,20));
        verschBtn.setPreferredSize(new Dimension(150,0));
        verschBtn.setBackground(new Color(0x2dce98));
        verschBtn.setForeground(Color.white);
        verschBtn.setUI(new ButtonDesign());
        verschBtn.setActionCommand(nextBtnName);
        verschBtn.setVisible(false);

        hochBtn = new JButton("+");
        hochBtn.setFont(new Font("", Font.BOLD,15));
        hochBtn.setUI(new TruppenButtonDesign());
        hochBtn.setActionCommand(hochBtnName);
        c.gridx = 1;
        c.gridy = 0;
        trpPanel.add(hochBtn, c);

        normalBtn = new JButton("Normal");
        normalBtn.setFont(new Font("", Font.BOLD,15));
        normalBtn.setUI(new TruppenButtonDesign());
        normalBtn.setActionCommand(normalBtnName);
        normalBtn.setVisible(false);
        c.gridx = 1;
        c.gridy = 0;
        trpPanel.add(normalBtn, c);
        
        blitzBtn = new JButton("Blitz");
        blitzBtn.setFont(new Font("", Font.BOLD,15));
        blitzBtn.setUI(new TruppenButtonDesign());
        blitzBtn.setActionCommand(blitzBtnName);
        blitzBtn.setVisible(false);
        c.gridx = 1;
        c.gridy = 1;
        trpPanel.add(blitzBtn, c);
        
        runterBtn = new JButton("-");
        runterBtn.setFont(new Font("", Font.BOLD,15));
        runterBtn.setUI(new TruppenButtonDesign());
        runterBtn.setActionCommand(runterBtnName);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        trpPanel.add(runterBtn, c);

        //funktioniert noch nicht
        phase = new JLabel("", SwingConstants.CENTER);
		phase.setText(Spieler.spieler.get(0).getName() + " ist dran mit setzen! Du kannst " + Spieler.spieler.get(0).getTruppen() + " Truppen versetzen.");
        phase.setFont(new Font("Calibri",Font.PLAIN,20));
        phase.setForeground(Color.white);
        phase.setLayout(new BorderLayout(0,10));
        phase.validate();

        // Anzeigen der Wuerfel

        //Angreiferwuerfel
        w1 = new JLabel();

        w2 = new JLabel();

        w3 = new JLabel();

        //Verteidigerwuerfel
        w4 = new JLabel();

        w5 = new JLabel();

        w6 = new JLabel();

        trpLabel = new JLabel(String.valueOf(truppen));
        trpLabel.setForeground(Color.white);
        trpLabel.setFont(new Font("Calibri",Font.PLAIN, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.ipadx = 15;
        trpPanel.add(trpLabel, c);

        spielerPanel.add(phase);

        btnPanel.add((truppenBtn),BorderLayout.WEST);
        btnPanel.add((nextBtn),BorderLayout.EAST);
        btnPanel.add((spielerPanel),BorderLayout.NORTH);

        waPanel.add(w1);
        waPanel.add(w2);
        waPanel.add(w3);
        wvPanel.add(w4);
        wvPanel.add(w5);
        wvPanel.add(w6);

        zwPanel.add(waPanel, BorderLayout.WEST);
        zwPanel.add(trpPanel, BorderLayout.CENTER);
        zwPanel.add(wvPanel, BorderLayout.EAST);

        btnPanel.add(zwPanel);
        ui.add(output);
        ui.add((btnPanel),BorderLayout.SOUTH);

        weltkarteActionListeners(this);

        refresh();
    }

    protected void weltkarteActionListeners(ActionListener e)
    {
    	truppenBtn.addActionListener(e);
    	nextBtn.addActionListener(e);
    	hochBtn.addActionListener(e);
    	runterBtn.addActionListener(e);
    	normalBtn.addActionListener(e);
    	blitzBtn.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    	String Klick = e.getActionCommand();

    	if(Klick.equals("truppenBtn"))
    	{
    		gebietskarten = new Gebietskarten();
    		gebietskarten.setVisible(true);
    	}
    	else if(Klick.equals("nextBtn"))
    	{
    		if (!postBattle && z == 0 && a == 1 && Spieler.spieler.get(si).getTruppen() == 0) 
    		{
    			trpLabel.setVisible(true);
    			hochBtn.setVisible(true);
    			runterBtn.setVisible(true);
    			normalBtn.setVisible(false);
    			blitzBtn.setVisible(false);
				System.out.println("Angriff!");
				Spieler.spieler.get(si).Angreifen(start, ziel);
				max = start.getTruppen();
				boolean test = Spieler.spieler.get(si).Angreifen(start, ziel);
				if( test == true)
				{
						waPanel.setVisible(true);
						wvPanel.setVisible(true);

					if(ziel.getTruppen() == 0)
					{
						player0 = Spieler.spieler.get(si);
						phase.setText("Der Angreifer hat gewonnen! Wähle die Truppen zum verschieben.");
					}
					else
					{
						phase.setText("Der Verteidiger hat gewonnen!");
					}
				}
				System.out.println("wie viele Truppen moechtest du versetzten?");
    			trpLabel.setText(String.valueOf(max - 1));
				zwPanel.setVisible(true);
				nextBtn.setText("OK");
				postBattle = true;
    		} else if (postBattle && z == 0 && a == 1 && Spieler.spieler.get(si).getTruppen() == 0) 
    			{
    				Spieler.spieler.get(si).TruppenBewegen(start, ziel);
    				waPanel.setVisible(false);
    				wvPanel.setVisible(false);
    				phase.setText("Du kannst erneut angreifen oder in die naechste Phase wechseln.");
	    			System.out.println("Stationierte Truppen" + start.getName() + ": " + ziel.getTruppen());
					System.out.println("Stationierte Truppen" + start.getName() + ": " + ziel.getTruppen());
					postBattle = false;
					nextBtn.setText("naechste Phase");
					a = 0;
					zwPanel.setVisible(false);
    		} else if (!postBattle && z == 0 && a == 0 && a != 1 && Spieler.spieler.get(si).getTruppen() == 0) 
    			{
					z++;
					nextBtn.setText("Zug Beenden");
					phase.setText("Du darfst 1x Truppen verschieben. Wähle die Laender aus oder beende deinen Zug.");
					System.out.println(Spieler.spieler.get(si) + " Truppen versetzten");
    		} else if (z == 1 && z != 2 && Spieler.spieler.get(si).getTruppen() == 0) 
    			{
//    			for (int g = 0; g < 42; g++) {
//					Laender.liste[g].setCurrentTruppen(Laender.liste[g].truppen);
//				}
    				z = 0;
    				zwPanel.setVisible(false);
    				nextBtn.setText("");
    				Spieler.spieler.get(si).istDran = false;
    				System.out.println("naechster spieler");

    			if (Spieler.spieler.size() == si + 1) 
    				{
    					si = 0;
    					Spieler.spieler.get(si).istDran = true;
    					Spieler.spieler.get(si).TruppenErhalten();
    					phase.setText(Spieler.spieler.get(si) + " ist dran und erhaelt " + Spieler.spieler.get(si).getTruppen() + " Truppen zum verteilen.");

    			} else 
    				{
    					Spieler.spieler.get(si + 1).istDran = true;
    					System.out.println(Spieler.spieler.get(si + 1).getName() + " ist dran.");
    					Spieler.spieler.get(si + 1).TruppenErhalten();
    					System.out.println(Spieler.spieler.get(si + 1).getName() + " erhaelt " + Spieler.spieler.get(si + 1).getTruppen());
    					phase.setText(Spieler.spieler.get(si + 1) + " ist dran und erhaelt " + Spieler.spieler.get(si + 1).getTruppen() + " Truppen zum verteilen.");

    				}
    		} else if (z == 2 && Spieler.spieler.get(si).getTruppen() == 0) 
    			{
    				cont = Spieler.spieler.get(si).TruppenBewegen(start, ziel);
    				nextBtn.setText("Zug Beenden");
    				zwPanel.setVisible(false);
    			}
    	}
    	else if(Klick.equals("angriffBtn"))
    			{
    				// do Angriff
    			}
    	else if(Klick.equals("verschBtn"))
    			{
    				// do Verschieben
    			}
    	else if(Klick.equals("hochBtn"))
    	{
    		if(truppen < max - 1)
    		{
    			truppen += 1;
    			trpLabel.setText(String.valueOf(truppen));
    		} 

    	}
    	else if(Klick.equals("runterBtn"))
    	{
    		if(truppen > 1)
    		{
    			truppen -= 1;
        		trpLabel.setText(String.valueOf(truppen));
    		}
    	}
    	else if(Klick.equals("normalBtn"))
    	{
    		if (z == 0 && a == 1) 
    		{
    			blitz = false;
    			normal = true;
    			System.out.println("Du hast normal gewaehlt");
    			phase.setText("Klicke nun auf Angriff!");

    		}
    	}
    	else if(Klick.equals("blitzBtn"))
    	{
    		if (z == 0 && a == 1) 
    		{
    			blitz = true;
    			normal = false;
    			phase.setText("Klicke nun auf Angriff!");
    			System.out.println("Du hast blitz gewaehlt");
    		}
    	}
    }

    public static ArrayList<Shape> getShapes()
    {
    	return shapeList;
    }

    public Area getOutline(Color ziel, BufferedImage bi, int toleranz) {
        // erstellt GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) 
        {
            for (int yy = 0; yy < bi.getHeight(); yy++) 
            {
                if (isIncluded(new Color(bi.getRGB(xx, yy)), ziel, toleranz)) 
                {
                    // xy von Bild = xy von Ziel
                    if (cont) 
                    {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else 
                    	{
                        	gp.moveTo(xx, yy);
                    	}
                    cont = true;
                } else 
                	{
                    	cont = false;
                	}
            }
            cont = false;
        }
        gp.closePath();

        // Area von GeneralPath
        return new Area(gp);
    }

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<>();

        PathIterator pi = shape.getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        while (!pi.isDone()) 
        {
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);
            int windingRule = pi.getWindingRule();
            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) 
            {
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) 
            	{
                	gp.lineTo(coords[0], coords[1]);
            	} else if (pathSegmentType == PathIterator.SEG_QUADTO) 
            		{
                		gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            		} else if (pathSegmentType == PathIterator.SEG_CUBICTO) 
            			{
            				gp.curveTo(
            						coords[0], coords[1],
            						coords[2], coords[3],
            						coords[4], coords[5]);
            			} else if (pathSegmentType == PathIterator.SEG_CLOSE) 
            				{
            					gp.closePath();
            					regions.add(new Area(gp));
            				} else 
            					{
            						System.err.println("Unexpected value! " + pathSegmentType);
            					}

            pi.next();
        }
        regions.remove(6);
        regions.remove(10);
        regions.remove(28);
        regions.remove(32);
        return regions;
    }

    class MousePositionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            refresh();

        }
    }
    class MouseClickListener implements MouseListener {

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		if (e.getButton() == MouseEvent.BUTTON1) 
    			{
    				for (int i = 0; i < shapeList.size();i++) 
    				{
    					Shape shape = shapeList.get(i);
    					if (shape.contains(e.getPoint())) 
    					{
    						laendHandler(i);
    					}
              }
             refresh();
    		}

    		else if (e.getButton() == MouseEvent.BUTTON3) 
    			{
    				for (int i = 0; i < shapeList.size();i++) 
    			{
    					Shape shape = shapeList.get(i);
    					if (shape.contains(e.getPoint())) 
    					{
//    				    	entfernenHandler(i);
    					}
    			}
    			}
         }

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

    }

    public static boolean isIncluded(Color ziel, Color pixel, int toleranz) {
        int rT = ziel.getRed();
        int gT = ziel.getGreen();
        int bT = ziel.getBlue();
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();
        return ((rP - toleranz <= rT) && (rT <= rP + toleranz)
                && (gP - toleranz <= gT) && (gT <= gP + toleranz)
                && (bP - toleranz <= bT) && (bT <= bP + toleranz));
    }

    private void refresh() {
        output.setIcon(new ImageIcon(getImage(player0)));
    }

   
    
    private BufferedImage getImage(Spieler player) {
    	BufferedImage bi = new BufferedImage(
                2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        g.drawImage(bild, 0, 0, output);
        g.fill(area);
        g.setColor(Color.BLACK);
        g.draw(area);

        // Einfaerben der SpielerLaender
        
        for (int b = 0; b < Spieler.spieler.size(); b++) {
        	if (Spieler.spieler.get(b) != player) {
        		for (int i = 0; i < Spieler.spieler.get(b).getLaender().size(); i++) 
                {
                	int j = 0;
                	for (int k = 0; k < 42; k++) 
                	{
                		if (Laender.liste[k] == Spieler.spieler.get(b).getLaender().get(i))
                		{
                			j = k;
                		}
                	}
                	if (Spieler.spieler.get(b) == Spieler.spieler1) {
                    	g.setColor(Color.BLUE.darker());
                	} else if (Spieler.spieler.get(b) == Spieler.spieler2) {
                    	g.setColor(Color.RED.darker());
                	} else if (Spieler.spieler.get(b) == Spieler.spieler3) {
                    	g.setColor(Color.YELLOW.darker());
                	} else if (Spieler.spieler.get(b) == Spieler.spieler4) {
                    	g.setColor(Color.GREEN.darker());
                	} else if (Spieler.spieler.get(b) == Spieler.spieler5) {
                    	g.setColor(Color.WHITE.darker());
                	}
                	g.fill(shapeList.get(j));
                }
        	}
    	}

        for (int i = 0; i < player.getLaender().size(); i++) 
        {
        	int j = 0;
        	for (int k = 0; k < 42; k++) 
        	{
        		if (Laender.liste[k] == player.getLaender().get(i))
        		{
        			j = k;
        		}
        	}
        	if (player == Spieler.spieler1) {
            	g.setColor(Color.BLUE.darker());
        	} else if (player == Spieler.spieler2) {
            	g.setColor(Color.RED.darker());
        	} else if (player == Spieler.spieler3) {
            	g.setColor(Color.YELLOW.darker());
        	} else if (player == Spieler.spieler4) {
            	g.setColor(Color.GREEN.darker());
        	} else if (player == Spieler.spieler5) {
            	g.setColor(Color.WHITE.darker());
        	}
        	g.fill(shapeList.get(j));
        }

        // Truppenanzahl in Laendern anzeigen

        g.setFont(new Font("Calibri", Font.BOLD,15));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Laender.liste[0].getTruppen()), 140, 90);
        g.drawString(String.valueOf(Laender.liste[1].getTruppen()), 660, 80);
        g.drawString(String.valueOf(Laender.liste[2].getTruppen()), 290, 65);
        g.drawString(String.valueOf(Laender.liste[3].getTruppen()), 355, 120);
        g.drawString(String.valueOf(Laender.liste[4].getTruppen()), 75, 90);
        g.drawString(String.valueOf(Laender.liste[5].getTruppen()), 410, 120);
        g.drawString(String.valueOf(Laender.liste[6].getTruppen()), 140, 135);
        g.drawString(String.valueOf(Laender.liste[7].getTruppen()), 650, 140);
        g.drawString(String.valueOf(Laender.liste[8].getTruppen()), 245, 140);
        g.drawString(String.valueOf(Laender.liste[9].getTruppen()), 725, 85);
        g.drawString(String.valueOf(Laender.liste[10].getTruppen()), 185, 140);
        g.drawString(String.valueOf(Laender.liste[11].getTruppen()), 600, 80);
        g.drawString(String.valueOf(Laender.liste[12].getTruppen()), 560, 130);
        g.drawString(String.valueOf(Laender.liste[13].getTruppen()), 350, 190);
        g.drawString(String.valueOf(Laender.liste[14].getTruppen()), 415, 190);
        g.drawString(String.valueOf(Laender.liste[15].getTruppen()), 660, 195);
        g.drawString(String.valueOf(Laender.liste[16].getTruppen()), 740, 200);
        g.drawString(String.valueOf(Laender.liste[17].getTruppen()), 140, 190);
        g.drawString(String.valueOf(Laender.liste[18].getTruppen()), 200, 210);
        g.drawString(String.valueOf(Laender.liste[19].getTruppen()), 480, 150);
        g.drawString(String.valueOf(Laender.liste[20].getTruppen()), 550, 210);
        g.drawString(String.valueOf(Laender.liste[21].getTruppen()), 416, 230);
        g.drawString(String.valueOf(Laender.liste[22].getTruppen()), 370, 230);
        g.drawString(String.valueOf(Laender.liste[23].getTruppen()), 640, 240);
        g.drawString(String.valueOf(Laender.liste[24].getTruppen()), 145, 250);
        g.drawString(String.valueOf(Laender.liste[25].getTruppen()), 200, 310);
        g.drawString(String.valueOf(Laender.liste[26].getTruppen()), 440, 310);
        g.drawString(String.valueOf(Laender.liste[27].getTruppen()), 655, 305);
        g.drawString(String.valueOf(Laender.liste[28].getTruppen()), 500, 290);
        g.drawString(String.valueOf(Laender.liste[29].getTruppen()), 595, 290);
        g.drawString(String.valueOf(Laender.liste[30].getTruppen()), 385, 340);
        g.drawString(String.valueOf(Laender.liste[31].getTruppen()), 735, 375);
        g.drawString(String.valueOf(Laender.liste[32].getTruppen()), 215, 375);
        g.drawString(String.valueOf(Laender.liste[33].getTruppen()), 670, 390);
        g.drawString(String.valueOf(Laender.liste[34].getTruppen()), 260, 355);
        g.drawString(String.valueOf(Laender.liste[35].getTruppen()), 445, 400);
        g.drawString(String.valueOf(Laender.liste[36].getTruppen()), 480, 370);
        g.drawString(String.valueOf(Laender.liste[37].getTruppen()), 520, 470);
        g.drawString(String.valueOf(Laender.liste[38].getTruppen()), 710, 470);
        g.drawString(String.valueOf(Laender.liste[39].getTruppen()), 770, 470);
        g.drawString(String.valueOf(Laender.liste[40].getTruppen()), 450, 470);
        g.drawString(String.valueOf(Laender.liste[41].getTruppen()), 220, 440);

        // Maus ueber Land bewegen
        try {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p1 = output.getLocationOnScreen();
            int x = p.x - p1.x;
            int y = p.y - p1.y;
            Point pointOnImage = new Point(x, y);
            for (Shape shape : shapeList) {
                if (shape.contains(pointOnImage)) {
                    g.setColor(Color.WHITE);
                    g.draw(shape);
                    break;

                }
            }
        } catch (Exception doNothing) {
        }

        g.dispose();

        return bi;
    }

    public JComponent getUI() {
        return ui;
    }

    public static void laendHandler (int i) {
    	si = 0;
    	//Spielstart phase
    	
    	if (erstercyclus) 
    	{
    		for (int j = 0; j < Spieler.spieler.size(); j++) 
    		{
        		if (Spieler.spieler.get(j).istDran) 
        		{
        			si = j;
        		}
    		}
    		ziel = Laender.liste[i];
    		Spieler.spieler.get(si).TruppenVerteilen(ziel);
    		System.out.println("Anfang: " + Spieler.spieler.get(si).getName() + " hat " + Spieler.spieler.get(si).getTruppen() + " uebrig");
    		phase.setText(Spieler.spieler.get(si).getName() + " ist dran mit setzen! Du hast noch " + Spieler.spieler.get(si).getTruppen() + " uebrig.");
    		if (si < Spieler.spieler.size() -1 && Spieler.spieler.get(si).getTruppen() == 0)
    		{
    			Spieler.spieler.get(si).istDran = false;
    			Spieler.spieler.get(si + 1).istDran = true;

    			//phase.setText(Spieler.spieler.get(si + 1).getName() + " ist dran mit setzen! Du kannst " + Spieler.spieler.get(si).getTruppen() + " Truppen versetzen.");
    			System.out.println(Spieler.spieler.get(si + 1).getName() + " ist dran mit setzen.");
    		} else if (si == Spieler.spieler.size() -1 && Spieler.spieler.get(si).getTruppen() == 0)
    			{
        			System.out.println(Spieler.spieler.get(0) + " ist dran.");
        			Spieler.spieler.get(0).TruppenErhalten();
        			phase.setText(Spieler.spieler.get(0) + " ist dran und erhaelt " + Spieler.spieler.get(0).getTruppen() + " Truppen zum verteilen.");
        			System.out.println(Spieler.spieler.get(0).getTruppen() + "!!!!");
        			Spieler.spieler.get(si).istDran = false;
        			Spieler.spieler.get(0).istDran = true;
        			erstercyclus = false;
//        			for (int g = 0; g < 42; g++) {
//    					Laender.liste[g].setCurrentTruppen(Laender.liste[g].truppen);
//    				}
        			return;
    			}
    		 return;
		}

    	for (int j = 0; j < Spieler.spieler.size(); j++) 
    	{
    		if (Spieler.spieler.get(j).istDran)
    		{
    			si = j;
    		}
    	}

    	// Truppen verteilen
    	
    	if (Spieler.spieler.get(si).getTruppen() > 0) 
    	{
    		ziel = Laender.liste[i];
    		Spieler.spieler.get(si).TruppenVerteilen(ziel);
    		System.out.println("Name Land: " + ziel.getName());
    		System.out.println("Truppe danach:" + ziel.getTruppen());
    		System.out.println(Spieler.spieler.get(si).getName() + " hat " + Spieler.spieler.get(si).getTruppen() + " uebrig");
    		if(Spieler.spieler.get(si).getTruppen() == 0) 
    		{
				nextBtn.setText("naechste Phase");
    			System.out.println("gehe in Kampfphase ueber!");
    			System.out.println("waehle die Laender aus");
    			phase.setText("Waehle die Laender fuer die Kampfphase aus!");
    		}
    		return;
    	} else 
    		{
			switch (z) {
			// Angriff
			case 0:
				nextBtn.setText("naechste Phase");
				switch (a) {
				case 0:
					start = Laender.liste [i];
					System.out.println(start.getNachbarn());
					System.out.println("erstes land ist " + start.getName());
					phase.setText("Das erste Land ist " + start.getName());
					a++;
					return;
				case 1:
					ziel = Laender.liste [i];
					System.out.println("zweites land ist " + ziel.getName());
					phase.setText("Das zweite Land ist " + ziel.getName() + ". Wähle Blitz oder Normalkampf.");
					int zielBesitzter = Spieler.spieler.indexOf(ziel.getBesitzer());
					int startBesitzter = Spieler.spieler.indexOf(start.getBesitzer());
					trpLabel.setVisible(false);
					nextBtn.setText("Angriff");
					hochBtn.setVisible(false);
					runterBtn.setVisible(false);
					normalBtn.setVisible(true);
					blitzBtn.setVisible(true);
					
					zwPanel.setVisible(true);
					// Spieler besiegt?
					if(Spieler.spieler.get(zielBesitzter).getLaender().size() == 0) 
					{
						Spieler.spieler.remove(Spieler.spieler.get(zielBesitzter));
					} else if (Spieler.spieler.get(startBesitzter).getLaender().size() == 0) 
						{
							Spieler.spieler.remove(Spieler.spieler.get(startBesitzter));
						}
				}
				return;
			case 1:
				System.out.println("Truppen versetzten");
				phase.setText("Waehle die Laender zum Truppen versetzen!");
				start = Laender.liste [i];
				max = start.getTruppen();
				System.out.println("erstes land ist " + start.getName());
				phase.setText("Das erste Land ist " + start.getName());
				z++;
				return;
			case 2:
				// Truppen versetzten
				nextBtn.setText("Verschieben");
    			trpLabel.setText(String.valueOf(max - 1));
    			zwPanel.setVisible(true);
				ziel = Laender.liste [i];
				System.out.println("zweites land ist " + ziel.getName());
				phase.setText("Das zweite Land ist " + ziel.getName());
				if (cont) 
				{
					z = 0;
				} else 
					{
						System.out.println("Truppen versetzten");
						z--;
						return;
					}
			}
			//Naechster spieler ist dran
			
			zwPanel.setVisible(false);
			nextBtn.setText("");
			Spieler.spieler.get(si).istDran = false;

			System.out.println("naechster spieler");
//			for (int g = 0; g < 42; g++) {
//				Laender.liste[g].setCurrentTruppen(Laender.liste[g].getTruppen());
//			}
			if (Spieler.spieler.size() == si + 1) 
			{
				si = 0;
				Spieler.spieler.get(si).istDran = true;
				Spieler.spieler.get(si).TruppenErhalten();

			} else 
				{
					Spieler.spieler.get(si + 1).istDran = true;
					System.out.println(Spieler.spieler.get(si + 1).getName() + " ist dran.");
					Spieler.spieler.get(si + 1).TruppenErhalten();
					System.out.println(Spieler.spieler.get(si + 1).getName() + " erhaelt " + Spieler.spieler.get(si + 1).getTruppen());
					phase.setText(Spieler.spieler.get(si + 1).getName() + " ist dran und erhaelt " + Spieler.spieler.get(si + 1).getTruppen());
				}
    	}
    }
    
//    public static void entfernenHandler (int i) {
//    	if (Spieler.spieler.get(si).getTruppen() > 1) 
//    	{
//    		ziel = Laender.liste[i];
//    		System.out.println("ziel ist: " + ziel.getName());
//    		System.out.println("current Truppen: " + ziel.getCurrentTruppen());
//    		if (ziel.getCurrentTruppen() < ziel.getTruppen())
//    		Spieler.spieler.get(si).TruppenEntfernen(ziel);
//		}
//    }
}