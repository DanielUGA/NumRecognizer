import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.stat.inference.TTest;

public class ImageToBitArrayProcessor {
	public static double[][] imageToBitArray(File file) {
		try 
		{
			BufferedImage bImg = ImageIO.read(file);
			Raster rast = bImg.getRaster();
			int wi = rast.getWidth();
			int he = rast.getHeight();
			double[][] bitArray = new double[wi][he];
			for(int countX = 0; countX < wi; countX++)
			{
				for(int countY = 0; countY < he; countY++)
				{
					bitArray[countX][countY] = rast.getSample(countX, countY, 0);
				}
			}
			return bitArray;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		ImageToBitArrayProcessor i = new ImageToBitArrayProcessor();
		TTest t = new TTest();
		System.out.println(t.tTest(imageToBitArray(new File("Images/341784255.138065.jpg"))[500],imageToBitArray(new File("Images/341784504.158383.jpg"))[500]));
	}

}
