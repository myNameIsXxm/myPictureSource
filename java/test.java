package com.xixi;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	public static void main(String[] args) {
		new test().changBackPic("D:\\abc.png");
	}

	public void changBackPic(String path) {
		File file = new File(path);
		int height = getPicHeight(file);
		int width = getPicWidth(file);
		try {
			BufferedImage bimg = ImageIO.read(new FileInputStream(path));
			FilteredImageSource fis = new FilteredImageSource(bimg.getSource(),
					new MyFilter(255));
			Image im = Toolkit.getDefaultToolkit().createImage(fis);
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D ig2 = bi.createGraphics();
			ig2.drawImage(im, 0, 0, width, height, null);
			ig2.dispose();
			delFile(path);
			ImageIO.write(bi, "PNG", new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("文件不存在");
			return false;
		}
		try {
			if (file.isFile()) {
				return file.delete();
			}
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
		return false;
	}

	public static int getPicHeight(File file) {
		try {
			return ImageIO.read(new FileInputStream(file)).getHeight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getPicWidth(File file) {
		try {
			return ImageIO.read(new FileInputStream(file)).getWidth();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

class MyFilter extends RGBImageFilter {
	int alpha = 0;

	public MyFilter(int alpha) {
		this.canFilterIndexColorModel = true;
		this.alpha = alpha;
	}

	public int filterRGB(int x, int y, int rgb) {
		DirectColorModel dcm = (DirectColorModel) ColorModel.getRGBdefault();
		int red = dcm.getRed(rgb);
		int green = dcm.getGreen(rgb);
		int blue = dcm.getBlue(rgb);
		int alp = dcm.getAlpha(rgb);
		if (red == 255 && blue == 255 && green == 255) {
			alpha = 0;
		} else {
			alpha = 255;
		}
		return alpha << 24 | red << 16 | green << 8 | blue;
	}
}
