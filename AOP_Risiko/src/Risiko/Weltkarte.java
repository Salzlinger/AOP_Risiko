package Risiko;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;

public class Weltkarte {

    private JComponent ui = null;
    JLabel output = new JLabel();
    public static final int SIZE = 550;
    BufferedImage bild;
    Area area;
    ArrayList<Shape> shapeList;
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

        area = getOutline(Color.WHITE, bild, 60);

        shapeList = separateShapeIntoRegions(area);
        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        

        output.addMouseMotionListener(new MousePositionListener());
        output.addMouseListener(new MouseClickListener());

        ui.add(output);

        refresh();
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
        regions.remove(21);
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
        for (int i = 0; i < Main.spieler1.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler1.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.BLUE);
        	g.fill(shapeList.get(j));
        }
        for (int i = 0; i < Main.spieler2.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler2.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.RED);
        	g.fill(shapeList.get(j));
        }
        for (int i = 0; i < Main.spieler3.getLaender().size(); i++) {
        	int j = 0;
        	for (int k = 0; k < 42; k++) {
        		if (Main.liste[k] == Main.spieler3.getLaender().get(i)) {
        			j = k;
        		}
        	}
        	g.setColor(Color.YELLOW);
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
	        	g.setColor(Color.GREEN);
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
	        	g.setColor(Color.WHITE);
	        	g.fill(shapeList.get(j));
	        }
        }

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
    		System.out.println("Anfang: " + Main.spieler.get(si).getName() + " hat " + Main.spieler.get(si).getTruppen() + " übrig");
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
    		System.out.println(Main.spieler.get(si).getName() + " hat " + Main.spieler.get(si).getTruppen() + " übrig");
    		if(Main.spieler.get(si).getTruppen() == 0) {
    			System.out.println("gehe in Kampfphase über!");
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
			//Näcster spieler ist drann.
			Main.spieler.get(si).istDrann = false;
			System.out.println("nächste spieler");
			if (Main.spieler.size() == si) {
				si = 0;
				Main.spieler.get(si).istDrann = true;
				Main.spieler.get(si).TruppenErhalten();
			} else {
			Main.spieler.get(si + 1).istDrann = true;
			System.out.println(Main.spieler.get(si + 1).getName() + " ist Drann.");
			Main.spieler.get(si + 1).TruppenErhalten();
			System.out.println(Main.spieler.get(si + 1).getName() + " erhält " + Main.spieler.get(si + 1).getTruppen());
			}
    	}
    }
    
    
}