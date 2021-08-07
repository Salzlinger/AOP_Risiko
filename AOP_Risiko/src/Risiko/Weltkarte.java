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

public class Weltkarte {

    private JComponent ui = null;
    private JLabel output;
    private JLabel phase;
    private JLabel w1;
    private JLabel w2;
    private JLabel w3;
    private JLabel w4;
    private JLabel w5;
    private JLabel w6;
    private JLabel trpLabel;

    private JPanel btnPanel;
    private JPanel spielerPanel;
    private JPanel wPanel;
    private JPanel trpPanel;

    private JButton truppenBtn;
    private JButton nextBtn;
    private JButton hochBtn;
    private JButton runterBtn;

    private String truppenBtnName = "truppenBtn";
    private String nextBtnName = "nextBtn";
    private String hochBtnName = "hochBtn";
    private String runterBtnName = "runterBtn";

    private Image image1;
    private Image newimage1;
    private Image image2;
    private Image newimage2;
    private Image image3;
    private Image newimage3;
    private Image image4;
    private Image newimage4;
    private Image image5;
    private Image newimage5;
    private Image image6;
    private Image newimage6;

    private ImageIcon wuerfel1;
    private ImageIcon wuerfel2;
    private ImageIcon wuerfel3;
    private ImageIcon wuerfel4;
    private ImageIcon wuerfel5;
    private ImageIcon wuerfel6;

    public static final int SIZE = 550;
    BufferedImage bild;
    Area area;
    static ArrayList<Shape> shapeList;
    private static int pointer;
    private static int z = 0;
	private static int a = 0;
	private static Laender start;
	private static Laender ziel;
	private static Boolean erstercyclus = true;


    public static int getPointer() {
		return pointer;
	}

	public Weltkarte() {
        try {
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public final void initUI() throws Exception {
        if (ui != null) {
            return;
        }
        String imagePath = "src\\img\\risk.png";
        bild = ImageIO.read(new File(imagePath));

        wuerfel1 = new ImageIcon("src\\img\\Wuerfel1.png");
        image1 = wuerfel1.getImage();
        newimage1 = image1.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel1 = new ImageIcon(newimage1);

        wuerfel2 = new ImageIcon("src\\img\\Wuerfel2.png");
        image2 = wuerfel2.getImage();
        newimage2 = image2.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel2 = new ImageIcon(newimage2);

        wuerfel3 = new ImageIcon("src\\img\\Wuerfel3.png");
        image3 = wuerfel3.getImage();
        newimage3 = image3.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel3 = new ImageIcon(newimage3);

        wuerfel4 = new ImageIcon("src\\img\\Wuerfel4.png");
        image4 = wuerfel4.getImage();
        newimage4 = image4.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel4 = new ImageIcon(newimage4);

        wuerfel5 = new ImageIcon("src\\img\\Wuerfel5.png");
        image5 = wuerfel5.getImage();
        newimage5 = image5.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel5 = new ImageIcon(newimage5);

        wuerfel6 = new ImageIcon("src\\img\\Wuerfel6.png");
        image6 = wuerfel6.getImage();
        newimage6 = image6.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        wuerfel6 = new ImageIcon(newimage6);

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

        wPanel = new JPanel();
        wPanel.setLayout(new GridLayout(1,6));
        wPanel.setBackground(new Color(0x3f47cc));

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

        nextBtn = new JButton("Zug beenden");
        nextBtn.setFont(new Font("Calibri", Font.PLAIN,20));
        nextBtn.setBackground(new Color(0x2dce98));
        nextBtn.setForeground(Color.white);
        nextBtn.setUI(new ButtonDesign());

        hochBtn = new JButton("+");
        hochBtn.setFont(new Font("", Font.BOLD,15));
        hochBtn.setUI(new TruppenButtonDesign());
        c.gridx = 1;
        c.gridy = 0;
        trpPanel.add(hochBtn, c);

        runterBtn = new JButton("-");
        runterBtn.setFont(new Font("", Font.BOLD,15));
        runterBtn.setUI(new TruppenButtonDesign());
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        trpPanel.add(runterBtn, c);

        phase = new JLabel("Spieler 1 ist am Zug!", SwingConstants.CENTER);
        phase.setFont(new Font("Calibri",Font.PLAIN,20));
        phase.setForeground(Color.white);
        phase.setLayout(new BorderLayout(0,10));

        w1 = new JLabel();
        w1.setIcon(wuerfel1);

        w2 = new JLabel();
        w2.setIcon(wuerfel2);

        w3 = new JLabel();
        w3.setIcon(wuerfel3);

        w4 = new JLabel();
        w4.setIcon(wuerfel4);

        w5 = new JLabel();
        w5.setIcon(wuerfel5);

        w6 = new JLabel();
        w6.setIcon(wuerfel6);

        trpLabel = new JLabel("5");
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

        wPanel.add(w1);
        wPanel.add(w2);
        wPanel.add(w3);
        wPanel.add(trpPanel);
        wPanel.add(w4);
        wPanel.add(w5);
        wPanel.add(w6);

        btnPanel.add(wPanel);
        ui.add(output);
        ui.add((btnPanel),BorderLayout.SOUTH);

        refresh();
    }

    public static ArrayList<Shape> getShapes() {
    	return shapeList;
    }

    public Area getOutline(Color ziel, BufferedImage bi, int toleranz) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (isIncluded(new Color(bi.getRGB(xx, yy)), ziel, toleranz)) {
                    //if (bi.getRGB(xx,yy)==targetRGB) {
                    if (cont) {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<>();

        PathIterator pi = shape.getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        while (!pi.isDone()) {
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);
            int windingRule = pi.getWindingRule();
            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) {
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) {
                gp.lineTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
                gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
                gp.curveTo(
                        coords[0], coords[1],
                        coords[2], coords[3],
                        coords[4], coords[5]);
            } else if (pathSegmentType == PathIterator.SEG_CLOSE) {
                gp.closePath();
                regions.add(new Area(gp));
            } else {
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
    		if (e.getButton() == MouseEvent.BUTTON1) {
             for (int i = 0; i < shapeList.size();i++) {
            	 Shape shape = shapeList.get(i);
            	 if (shape.contains(e.getPoint())) {
            		 laendHandler(i);
            	 }
              }
    		}
    		else if (e.getButton() == MouseEvent.BUTTON3) {
             for (int i = 0; i < shapeList.size();i++) {
            	 Shape shape = shapeList.get(i);
            	 if (shape.contains(e.getPoint())) {
            		 System.out.println("RechtsKlick auf Shape " + i);
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
        output.setIcon(new ImageIcon(getImage()));
    }

    private BufferedImage getImage() {
        BufferedImage bi = new BufferedImage(
                2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        g.drawImage(bild, 0, 0, output);
        g.fill(area);
        g.setColor(Color.BLACK);
        g.draw(area);

        // Einf�rben der SpielerL�nder

        for (int i = 0; i < Main.spieler1.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler1.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.BLUE.darker());
        	g.fill(shapeList.get(j));
        }
        for (int i = 0; i < Main.spieler2.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler2.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.RED.darker());
        	g.fill(shapeList.get(j));
        }
        for (int i = 0; i < Main.spieler3.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler3.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.YELLOW.darker());
        	g.fill(shapeList.get(j));
        }
        if(Main.spieler.size() > 3) {
	        for (int i = 0; i < Main.spieler4.getLaender().size(); i++) {
	        	int j = 0;
	        	for (int k = 0; k < 42; k++) {
	        		if (Main.liste[k] == Main.spieler4.getLaender().get(i)) {
	        			j = k;
	        		}
	        	}
	        	g.setColor(Color.GREEN.darker());
	        	g.fill(shapeList.get(j));
	        }
        }
        if (Main.spieler.size() > 4) {
	        for (int i = 0; i < Main.spieler5.getLaender().size(); i++) {
	        	int j = 0;
	        	for (int k = 0; k < 42; k++) {
	        		if (Main.liste[k] == Main.spieler5.getLaender().get(i)) {
	        			j = k;
	        		}
	        	}
	        	g.setColor(Color.WHITE.darker());
	        	g.fill(shapeList.get(j));
	        }
        }

        // Truppenanzahl in L�ndern anzeigen

        int i = Main.liste[0].getTruppen();
        g.setFont(new Font("Calibri", Font.BOLD,15));
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(i), 100, 100);

        // Maus �ber Land bewegen
        try {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p1 = output.getLocationOnScreen();
            int x = p.x - p1.x;
            int y = p.y - p1.y;
            Point pointOnImage = new Point(x, y);
            for (Shape shape : shapeList) {
                if (shape.contains(pointOnImage)) {
                    g.setColor(Color.GREEN.darker());
                    g.fill(shape);
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
		boolean cont = false;
    	int si = 0;
    	//Spielstart phase
    	if (erstercyclus) {
    		for (int j = 0; j < Main.spieler.size(); j++) {
        		if (Main.spieler.get(j).istDrann) {
        			si = j;
        		}
    		}
    		ziel = Main.liste[i];
    		Main.spieler.get(si).TruppenVerteilen(ziel);
    		System.out.println("Anfang: " + Main.spieler.get(si).getName() + " hat " + Main.spieler.get(si).getTruppen() + " �brig");
    		if (si < Main.spieler.size() -1 && Main.spieler.get(si).getTruppen() == 0){
    			Main.spieler.get(si).istDrann = false;
    			Main.spieler.get(si + 1).istDrann = true;
    			System.out.println(Main.spieler.get(si + 1).getName() + " ist Drann mit setzten.");
    		} else if (si == Main.spieler.size() -1 && Main.spieler.get(si).getTruppen() == 0){
        		System.out.println(Main.spieler.get(0) + " ist Drann.");
        		Main.spieler.get(0).TruppenErhalten();
        		System.out.println(Main.spieler.get(0).getTruppen() + "!!!!");
        		Main.spieler.get(si).istDrann = false;
        		Main.spieler.get(0).istDrann = true;
        		erstercyclus = false;
        		return;
        	}
    		 return;
		}

    	for (int j = 0; j < Main.spieler.size(); j++) {
    		if (Main.spieler.get(j).istDrann) {
    			si = j;
    		}
    	}

    	// Truppen verteilen
    	if (Main.spieler.get(si).getTruppen() > 0) {
    		ziel = Main.liste[i];
    		Main.spieler.get(si).TruppenVerteilen(ziel);
    		System.out.println("Name Land: " + ziel.getName());
    		System.out.println("Truppe danach:" + ziel.getTruppen());
    		System.out.println(Main.spieler.get(si).getName() + " hat " + Main.spieler.get(si).getTruppen() + " �brig");
    		if(Main.spieler.get(si).getTruppen() == 0) {
    			System.out.println("gehe in Kampfphase �ber!");
    		}
    		return;
    	} else {
			switch (z) {
			// Angriff
			case 0:
				switch (a) {
				case 0:
					System.out.println("Angriff!");
					start = Main.liste [i];
					System.out.println(start.getNachbarn());
					System.out.println("erstes land ist " + start.getName());
					a++;
					return;
				case 1:
					ziel = Main.liste [i];
					System.out.println("zweites land ist " + ziel.getName());
					int zielBesitzter = Main.spieler.indexOf(ziel.getBesitzer());
					int startBesitzter = Main.spieler.indexOf(start.getBesitzer());
					System.out.println("anzahl  davor " + start.getTruppen());
					System.out.println("anzahl  davor " + ziel.getTruppen());
					Main.spieler.get(si).Angreifen(start, ziel);
					System.out.println("anzahl " + start.getTruppen());
					System.out.println("anzahl " + ziel.getTruppen());
					// Spieler besiegt?
					if(Main.spieler.get(zielBesitzter).getLaender().size() == 0) {
						Main.spieler.remove(Main.spieler.get(zielBesitzter));
					} else if (Main.spieler.get(startBesitzter).getLaender().size() == 0) {
						Main.spieler.remove(Main.spieler.get(startBesitzter));
					}
					// notification: attack again?
				}
				if (false) {
					return;
				} else {
					z++;
					System.out.println(Main.spieler.get(si) + " Truppen versetzten");
					return;
				}
			case 1:
				System.out.println("Truppen versetzten");
				start = Main.liste [i];
				System.out.println("erstes land ist " + start.getName());
				z++;
				return;
			case 2:
				// Truppen versetzten
				ziel = Main.liste [i];
				System.out.println("zweites land ist " + ziel.getName());
				cont = Main.spieler.get(si).TruppenBewegen(start, ziel);
				if (cont) {
					z = 0;
				} else {
					return;
				}
			}
			//N�cster spieler ist drann.
			Main.spieler.get(si).istDrann = false;
			System.out.println("n�chste spieler");
			if (Main.spieler.size() == si) {
				si = 0;
				Main.spieler.get(si).istDrann = true;
				Main.spieler.get(si).TruppenErhalten();
			} else {
			Main.spieler.get(si + 1).istDrann = true;
			System.out.println(Main.spieler.get(si + 1).getName() + " ist Drann.");
			Main.spieler.get(si + 1).TruppenErhalten();
			System.out.println(Main.spieler.get(si + 1).getName() + " erh�lt " + Main.spieler.get(si + 1).getTruppen());
			}
    	}
    }


}
