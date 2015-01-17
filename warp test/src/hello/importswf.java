package hello;

import com.flagstone.transform.Background;
import com.flagstone.transform.Movie;
import com.flagstone.transform.MovieHeader;
import com.flagstone.transform.Place2;
import com.flagstone.transform.ShowFrame;
import com.flagstone.transform.datatype.Bounds;
import com.flagstone.transform.datatype.Color;
import com.flagstone.transform.datatype.WebPalette;
import com.flagstone.transform.font.DefineFont2;
import com.flagstone.transform.text.DefineText2;
import com.flagstone.transform.util.font.AWTDecoder;
import com.flagstone.transform.util.font.Font;
import com.flagstone.transform.util.text.CharacterSet;
import com.flagstone.transform.util.text.TextTable;

public class importswf {

	public static void main(String[] args) {
		int uid = 1;
		int layer = 1;

		final String str = "The quick, brown fox jumped over the lazy dog.";
		final Color color = WebPalette.BLACK.color();

		final String fontName = "Arial";
		final int fontSize = 24;
		final int fontStyle = java.awt.Font.PLAIN;

		// Load the AWT font.
		final AWTDecoder fontDecoder = new AWTDecoder();
		fontDecoder.read(new java.awt.Font(fontName, fontStyle, fontSize));
		final Font font = fontDecoder.getFonts().get(0);

		// Create a table of the characters displayed.
		final CharacterSet set = new CharacterSet();
		set.add(str);

		// Define the font containing only the characters displayed.
		DefineFont2 fontDef = font.defineFont(uid++, set.getCharacters());

		// Generate the text field used for the button text.
		final TextTable textGenerator = new TextTable(fontDef, fontSize * 20);
		DefineText2 text = textGenerator.defineText(uid++, str, color);

		// Set the screen size to match the text with padding so the 
		// text does not touch the edge of the screen.
		int padding = 1000;
		int screenWidth = text.getBounds().getWidth() + padding;
		int screenHeight = text.getBounds().getHeight() + padding;

		// Position the text in the center of the screen.
		final int xpos = padding / 2;
		final int ypos = screenHeight / 2;

		MovieHeader header = new MovieHeader();
		header.setFrameRate(1.0f);
		header.setFrameSize(new Bounds(0, 0, screenWidth, screenHeight));

		// Add all the objects together to create the movie.
		Movie movie = new Movie();
		movie.add(header);
		movie.add(new Background(WebPalette.LIGHT_BLUE.color()));
		movie.add(fontDef);
		movie.add(text);
		movie.add(Place2.show(text.getIdentifier(), layer++, xpos, ypos));
		movie.add(ShowFrame.getInstance());

		movie.encodeToFile("example.swf");

	}

}
