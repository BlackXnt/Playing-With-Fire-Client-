package misc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import map.Tile;

public class Util {
	public static Image[][] splitSprites(ImageIcon sprite, int rows, int cols) {
		Image[][] sprites = new Image[rows][cols];
		int imageHeight = sprite.getIconHeight();
		int imageWidth = sprite.getIconWidth();
		BufferedImage bimg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics gr = bimg.getGraphics();
		gr.drawImage(sprite.getImage(), 0, 0, null);
		int px = 0, py = 0, w = imageWidth / cols, h = imageHeight / rows;
		for (int i = 0; i < rows; i++) {
			px = 0;
			for (int j = 0; j < cols; j++) {

				sprites[i][j] = bimg.getSubimage(px, py, w, h);
				px += w;

			}
			py += h;
		}
		return sprites;
	}
}
