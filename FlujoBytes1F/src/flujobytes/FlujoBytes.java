package flujobytes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class FlujoBytes {

	private File fileImage = null;
	private File fileModified = null;

	
	public  void espejo() throws IOException
	{
		fileImage = new File("/Users/alvarobanofos/lena.jpg");
		fileModified = new File("/Users/alvarobanofos/lena90.jpg");
		
		if(fileImage != null) {
			BufferedImage img = ImageIO.read(fileImage);
			BufferedImage imgRotated = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
			for(int j=0; j<img.getHeight(); j++)
			{
				for(int i=0; i<img.getWidth(); i++){
					int rgb = img.getRGB(i, j);
					imgRotated.setRGB(imgRotated.getWidth()-i-1, j, rgb);
				}
			}
			ImageIO.write(imgRotated, "jpg", fileModified);
			
		}	
		
	}
	
	public  void rotacion90() throws IOException
	{
		fileImage = new File("/Users/alvarobanofos/lena.jpg");
		fileModified = new File("/Users/alvarobanofos/lena90.jpg");
		
		if(fileImage != null) {
			BufferedImage img = ImageIO.read(fileImage);
			BufferedImage imgRotated = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
			for(int j=0; j<img.getHeight(); j++)
			{
				for(int i=0; i<img.getWidth(); i++){
					int rgb = img.getRGB(i, j);
					imgRotated.setRGB(imgRotated.getWidth()-j-1, imgRotated.getHeight()-i-1, rgb);
				}
			}
			ImageIO.write(imgRotated, "jpg", fileModified);
			
		}	
		
	}
	
	public static void main(String[] args) {
		
		FlujoBytes app = new FlujoBytes();
		try {
			app.rotacion90();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
