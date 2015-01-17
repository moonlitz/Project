package hello;

import java.awt.geom.AffineTransform;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.Interpolation;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.Warp;
import javax.media.jai.WarpAffine;

public class WarpAffineTest {

	public static main(String[] args) {
		 double m00 = 0.8;
	     double m10 = 0.3;
	     double m01 = -0.7;
	     double m11 = 1.4;
	     double m02 = 230.3;
	     double m12 = -115.7;
	     AffineTransform transform = new AffineTransform(m00, m10,
	                                                     m01, m11,
	                                                     m02, m12);
	     Warp warp = new WarpAffine(transform);
	     // Create the interpolation parameter.
	     Interpolation interp = new InterpolationNearest(8);
	     // Create the ParameterBlock.
	     ParameterBlock pb = new ParameterBlock();
	     pb.addSource(src);
	     pb.add(warp);
	     pb.add(interp);
	     // Create the warp operation.
	     return (RenderedImage)JAI.create("warp", pb);
	}

}
