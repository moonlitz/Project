package hello;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class mousedrug extends JPanel {

    private static final String TITLE = "Drag me!";
    private static final int W = 640;
    private static final int H = 480;
    private Point origin = new Point(W / 2, H / 2);
    private Point mousePt;

    public mousedrug() {
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
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, origin.y, getWidth(), origin.y);
        g.drawLine(origin.x, 0, origin.x, getHeight());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame f = new JFrame(TITLE);
                f.add(new mousedrug());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
	}

}
