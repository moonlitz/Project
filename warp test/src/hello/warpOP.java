package hello;


import java.awt.Frame;
import java.awt.geom.AffineTransform;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;

import javax.media.jai.Interpolation;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PerspectiveTransform;
import javax.media.jai.RenderedOp;
import javax.media.jai.Warp;
import javax.media.jai.WarpAffine;
import javax.media.jai.WarpPerspective;
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
public class warpOP {
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
        
        double m00 = 1.0;
        double m01 = 0.0;
        double m02 = 0.0; 
        double m10 = 0.0;
        double m11 = 1.0; 
        double m12 = 0.0; 
        double m20 = 0.0; 
        double m21 = 0.0; 
        double m22 = 1.0;
         
        
       // warp = WarpPolynomial.createWarp(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        
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
        
        int h = image1.getHeight();
        System.out.println(h);
        
        float[] coeffs = { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F };
        
        
      //    WarpPolynomial warp = new WarpPolynomial();
      //  warp = WarpPolynomial.createWarp(coeffs, 0, null, 0, 0, 0, 0, 0, 0, 0);
        PerspectiveTransform p = new PerspectiveTransform().getQuadToQuad(0.0, 0.0, image1.getWidth(), 0.0, 0.0, image1.getHeight(), image1.getWidth(), image1.getHeight(), 100.0, 100.0, image1.getWidth(), 0.0, 100.0, image1.getHeight()-100, image1.getWidth()-100, image1.getHeight());
        WarpPerspective wp = new WarpPerspective(p);
        
        ParameterBlock params = new ParameterBlock();
        params.addSource(image1);
        
        params.add(wp);       
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
    }
}