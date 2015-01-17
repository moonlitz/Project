package hello;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;

import javax.media.jai.Interpolation;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.Warp;
import javax.media.jai.WarpAffine;
import javax.media.jai.WarpPolynomial;

import com.sun.media.jai.codec.FileSeekableStream;

import javax.media.jai.widget.ScrollingImagePanel;
/**
 * This program decodes an image file of any JAI supported
 * formats, such as GIF, JPEG, TIFF, BMP, PNM, PNG, into a
 * RenderedImage, scales the image by 2X with bilinear
 * interpolation, and then displays the result of the scale
 * operation.
 */
public class JAISanpleProgram {
    /** The main method. */
    public static void main(String[] args) {
        /* Validate input. */
      /*  if (args.length != 1) {
            System.out.println("Usage: java JAISampleProgram " +
                               "input_image_filename");
            System.exit(-1);
        }
        /*
         * Create an input stream from the specified file name
         * to be used with the file decoding operator.
         */
        FileSeekableStream stream = null;
        try {
            stream = new FileSeekableStream("src/ACU.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
       /* WarpPolynomial warp;
        float[] coeffs = { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F };*/
        
        double m00 = 1.0;
        double m10 = 0.7;
        double m01 = 0.0;
        double m11 = 1.0;
        double m02 = 0;
        double m12 = 0;
        AffineTransform transform = new AffineTransform(m00, m10,
                                                        m01, m11,
                                                        m02, m12);
        /*
        m00	The x coordinate scale element
		m10	The y coordinate shear element
		m01	The x coordinate shear element
		m11	The y coordinate scale element
		m02 The x coordinate translate element
		m12 The y coordinate translate element
         * */
        Warp warp = new WarpAffine(transform);
        // Create the interpolation parameter.
        Interpolation interp = new InterpolationNearest();
   // Create the ParameterBlock and add the parameters to it.

        /* Create an operator to decode the image file. */
        RenderedOp image1 = JAI.create("stream", stream);
        /*
         * Create a standard bilinear interpolation object to be
         * used with the "scale" operator.
         */
  //      Interpolation interp = Interpolation.getInstance(
   //                                Interpolation.INTERP_BILINEAR);
        /**
         * Stores the required input source and parameters in a
         * ParameterBlock to be sent to the operation registry,
         * and eventually to the "scale" operator.
         */
      //  warp = WarpPolynomial.createWarp(coeffs, 0, null, 0, 0, 0, 0, 0, 0, 0);
        
        ParameterBlock params = new ParameterBlock();
        params.addSource(image1);
              
        /*params.add(1.0F);         // x scale factor
        params.add(1.0F);         // y scale factor
        params.add(0.0F);         // x translate
        params.add(0.0F);         // y translate*/
        
        params.add(warp);
        
        params.add(new InterpolationNearest());
        
      //  params.add(interp);       // interpolation method
        /* Create an operator to scale image1. */
        RenderedOp image2 = JAI.create("warp", params);
        /* Get the width and height of image2. */
        int width = image2.getWidth();
        int height = image2.getHeight();
        /* Attach image2 to a scrolling panel to be displayed. */
        ScrollingImagePanel panel = new ScrollingImagePanel(
                                        image2, width, height);
        /* Create a frame to contain the panel. */
        Frame window = new Frame("JAI Sample Program");
        window.add(panel);
        window.pack();
        window.show();
        
      /*  addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
          });*/
    }
}