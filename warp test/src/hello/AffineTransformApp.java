package hello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.media.jai.WarpPerspective;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AffineTransformApp extends JFrame {
  DisplayPanel displayPanel;

  public AffineTransformApp() {
    super();
   Container container = getContentPane();

    displayPanel = new DisplayPanel();
    container.add(displayPanel);

    
    
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    
    setSize(displayPanel.getWidth(), displayPanel.getHeight()-220);
    setVisible(true);
  }

  public static void main(String arg[]) {
    new AffineTransformApp();
  }
}

class DisplayPanel extends JLabel {
	
  Image displayImage;

  BufferedImage biSrc, biDest;

  BufferedImage bi;

  Graphics2D big;

  AffineTransform transform;
  
  double scalex = 1.0;

  double scaley = 1.0;

  double shearx = 1.0;

  double sheary = 1.0;
  
  ////
  
  WarpPerspective warp;
  
  float[] xCoeffs;

  float[] yCoeffs;

  float preScaleX;

  float preScaleY;

  float postScaleX;

  float postScaleY;

  int degree;
  
  

  DisplayPanel() {
	  
    setBackground(Color.black);
    loadImage();
    setSize(displayImage.getWidth(this), displayImage.getWidth(this)); // panel
    createBufferedImages();

    transform = new AffineTransform();
    
  //  warp = new WarpPerspective();
  }

  public void loadImage() {
    displayImage = Toolkit.getDefaultToolkit().getImage(
        "src/ACU.jpg");
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(displayImage, 1);
    try {
      mt.waitForAll();
    } catch (Exception e) {
      System.out.println("Exception while loading.");
    }

    if (displayImage.getWidth(this) == -1) {
      System.out.println(" Missing .jpg file");
      System.exit(0);
    }
  }

  public void createBufferedImages() {
    biSrc = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_RGB);

    big = biSrc.createGraphics();
    big.drawImage(displayImage, 0, 0, this);

    bi = biSrc;

    biDest = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_RGB);
  }

  
  public void update(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    paintComponent(g);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(bi, 0, 0, this);
  }
}
