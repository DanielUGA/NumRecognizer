import org.apache.commons.math3.stat.inference.TTest;

public class ImageComparison
{
	public boolean imagesEqual(double[][] image1, double[][] image2, double alpha)
	{
		TTest t = new TTest();
		double pValAgg = 0;
		for(int index = 0; index < image1.length; index++)
		{
			pValAgg += t.tTest(image1[index], image2[index]);
		}
		return pValAgg < alpha * image1.length;
	}
}
