package com.xixi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChangeColor {
	public static void main(String[] args) {
		new ChangeColor().changePicColorByOldPic("D:\\abc.png", 0, 0, 0, 255, 0, 0);
	}

	public void changePicColorByOldPic(String filepath, int OR, int OG, int OB, int R, int G, int B) {
		try {
			BufferedImage bf = ImageIO.read(new FileInputStream(filepath));
			File file = new File(filepath);
			BufferedImage bfm = ImageIO.read(new FileInputStream(file));
			int height = bfm.getHeight();
			int width = bfm.getWidth();
			for (int h = 0; h < height; h++) {
				for (int w = 0; w < width; w++) {
					Color c = new Color(bf.getRGB(w, h));
					if (c.getBlue() == OR || c.getRed() == OG || c.getGreen() == OB) {
						bf.setRGB(w, h, new Color(R, G, B).getRGB());
					}
				}
			}
			String[] array = filepath.split("\\.");
			String gs = array[array.length - 1];
			filepath = filepath.replace("." + gs, "_changeColor." + gs);
			ImageIO.write(bf, "PNG", new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}