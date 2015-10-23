import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.ImageSearcher;
import net.semanticmetadata.lire.imageanalysis.PHOG;
import net.semanticmetadata.lire.impl.GenericFastImageSearcher;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
    public static void main(String[] args) throws IOException {
        BufferedImage img = null;
            File f = new File("OtherImages/Lion.jpg");
            if (f.exists()) {
                try {
                    img = ImageIO.read(f);
                    
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
      

//        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("indexPath")));
        IndexReader ir = DirectoryReader.open(FSDirectory.open(new File("index")));
        ImageSearcher searcher = new GenericFastImageSearcher(30, PHOG.class);

        // searching with a image file ...
        ImageSearchHits hits = searcher.search(img, ir);
        // searching with a Lucene document instance ...
        for (int i = 0; i < hits.length(); i++) {
           // String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
        	String fileName = hits.doc(i).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            System.out.println(hits.score(i) + ": \t" + fileName);
        }
    }
}