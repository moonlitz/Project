package hello;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class LoadAndDrug extends JPanel {
	
    //load
    BufferedImage img;

    private static final String TITLE = "Drag me!";
    private static final int W = 1024;
    private static final int H = 600;
    private Point origin = new Point(-W/2,-H/2);
    private Point mousePt;
    
     public LoadAndDrug() {
    	
            //load
        try {
			File file = new File("src/ACU1.jpg");
			System.out.println("Is the file there ? " + file.exists());
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Image load failed");
		}

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                origin.setLocation(origin.x + dx, origin.y + dy);
                mousePt = e.getPoint();
                repaint();
            }
        });
        
    }


    public Dimension getPreferredSize() {
        return new Dimension(W, H);
       // return new Dimension(img.getWidth(null), img.getHeight(null));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
     //   g.drawLine(0, origin.y, getWidth(), origin.y);
     //   g.drawLine(origin.x, 0, origin.x, getHeight());
    //    if(origin.x > 1920)origin.x-=1920;
        g.drawImage(img, origin.x, origin.y, null);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame f = new JFrame(TITLE);
                f.add(new LoadAndDrug());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
	}

}
