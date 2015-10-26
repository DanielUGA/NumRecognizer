import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import net.semanticmetadata.lire.imageanalysis.FCTH;
import net.semanticmetadata.lire.imageanalysis.PHOG;
import net.semanticmetadata.lire.impl.GenericDocumentBuilder;
import net.semanticmetadata.lire.utils.FileUtils;
import net.semanticmetadata.lire.utils.LuceneUtils;

@SuppressWarnings("unused")
public class Indexer 
{

	public static void main(String[] args) 
	{
		Indexer m = new Indexer();
		m.IndexPhog();
	}

	public void IndexPhog()
	{
		try {
			ArrayList<String> images = FileUtils.getAllImages(
					new File("Images"), true);
			GenericDocumentBuilder gdb = new GenericDocumentBuilder(FCTH.class);
			IndexWriter iw = LuceneUtils.createIndexWriter("index", true,
					LuceneUtils.AnalyzerType.WhitespaceAnalyzer);
			for (Iterator<String> it = images.iterator(); it.hasNext();) 
			{
				String imageFilePath = it.next();
				System.out.println("Indexing " + imageFilePath);
				try
				{
					BufferedImage img = ImageIO.read(new FileInputStream(
							imageFilePath));
					Document document = gdb.createDocument(img, imageFilePath);
					iw.addDocument(document);
				} catch (Exception e) {
					System.err.println("Error reading image or indexing it.");
					e.printStackTrace();
				}
			}
			// closing the IndexWriter
			iw.close();
			System.out.println("Finished indexing.");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
