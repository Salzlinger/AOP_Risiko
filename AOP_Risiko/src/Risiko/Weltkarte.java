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
    static ArrayList<Shape> shapeList;

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
            		 System.out.println("LinksKlick auf Shape " + i);
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
        g.setColor(Color.WHITE.darker());
        g.fill(area);
        g.setColor(Color.BLACK);
        g.draw(area);
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
}