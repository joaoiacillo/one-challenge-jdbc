package app.vercel.joaoiacillo.alurahotel.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Utils class for image and icons operations.
 * @author João Iacillo
 */
public class Images {

	public static Image resizeImage(Image img, int newWidth, int newHeight) {
		return img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	}
	
	public static ImageIcon resizeImageIcon(ImageIcon icon, int newWidth, int newHeight) {
		Image iconImage = icon.getImage();
		Image resizedIconImage = Images.resizeImage(iconImage, newWidth, newHeight);
		return new ImageIcon(resizedIconImage);
	}
	
}
