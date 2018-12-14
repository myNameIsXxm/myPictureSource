package com.xixi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brighteen {
	public static void main(String[] args) {
		try {
			new Brighteen().brighteen("D:\\jazm.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void brighteen(String path) throws IOException {
		BufferedImage img = ImageIO.read(new File(path));
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int rgb = img.getRGB(i, j);
				int r = rgb >> 16 & 255;
				int g = rgb >> 8 & 255;
				int b = rgb & 255;
				r = getRGB(r);
				g = getRGB(g);
				b = getRGB(b);
				int nc = r << 16 | g << 8 | b;
				img.setRGB(i, j, nc);
			}
		}
		String[] array = path.split("\\.");
		String gs = array[array.length - 1];
		path = path.replace("." + gs, "_bright." + gs);
		ImageIO.write(img, "jpeg", new File(path));
	}

	public int getRGB(int i) {
		i = i * 7 / 5;
		return i > 255 ? 255 : i;
	}

}