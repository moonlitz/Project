package hello;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class ImageWarp extends Component {

	BufferedImage img;

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public ImageWarp() {

		try {
			File file = new File("src/ACU.jpg");
			System.out.println("Is the file there ? " + file.exists());
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Image load failed");
		}

	}

	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100, 100);
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null));
			//return new Dimension(500, 500);
		}
	}
	

	public static void main(String[] args) {

		 JFrame frame = new JFrame("Load Image Sample");
		 
		 frame.addWindowListener(new WindowAdapter(){ public void
		 windowClosing(WindowEvent e) { System.exit(0); } });
		 
		 frame.add(new LoadImageApp()); 
		 frame.pack(); 
		 frame.setVisible(true);
		 
	}
	
}
