package DefaultPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import org.apache.commons.lang3.text.StrTokenizer;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class UPM_PDF_Verification 
{
	PDFTextStripper pdfStripper = null;
	PDDocument pdDoc = null;
	COSDocument cosDoc = null;
	String parsedText = null;
	
	public String PDF_READ(String path) throws IOException
	{
		try
		{
		
		File file = new File(path);
		PDFParser parser = new PDFParser(new FileInputStream(file));
		
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();	
		pdfStripper.setStartPage(1);
		//pdfStripper.setEndPage(1);
		
		pdDoc = new PDDocument(cosDoc);
		parsedText = pdfStripper.getText(pdDoc);
		parsedText = parsedText.toString();
		
		System.out.println(parsedText);	
		
		}
		
	 catch (MalformedURLException e2) 
	 {
		System.err.println("URL string could not be parsed "+e2.getMessage());
	 }
	 catch (IOException e) 
	 {
		System.err.println("Unable to open PDF Parser. " + e.getMessage());
		try 
		{
			if (cosDoc != null)
				cosDoc.close();
			if (pdDoc != null)
				pdDoc.close();
			} 
			catch (Exception e1) 
			{
			e.printStackTrace();
			}
		}
		return parsedText;
	}
	
	public String PDF_READ_2(File Path) throws IOException
	{
		//File pdfFile = new File(Path);
		PDDocument doc = PDDocument.load(Path);
		PDFTextStripper stripper = new PDFTextStripper();
		
		String pdftext = stripper.getText(doc);
		
		doc.close();
		System.out.println(pdftext);
		return pdftext;
	}
	
	public static String PDF_READ_3(String Path) throws Exception
	{
		String S1 = null;
		PdfReader reader = new PdfReader(Path);
        
        for(int i = 1; i <= reader.getNumberOfPages(); i++) 
        {
        	S1 = S1 + PdfTextExtractor.getTextFromPage(reader, i);
        	//System.out.println(S1);
        }
        return S1;
    }
	
	
	
	/*public static void main(String args[]) throws Throwable 
	{
		UPM_PDF_Verification OB1 = new UPM_PDF_Verification();
		
		try 
		{
			String Path = "C:/Users/28891/Downloads/Details Partner Test UPM Test Contract(8).pdf";
			File pdfFile = new File(Path);
			
			String pdf = OB1.PDF_READ_3(Path);
			
			System.out.println(pdf);
			String[] lines = pdf.split("\\r?\\n", -1);
        				
        	String Label = "Vorname/Firmenname";
        	String value = "Test UPM";
        	
        	int i;
			for(i=0; i< lines.length; i++)
			{
				System.out.println(lines[i]+i);
				if(lines[i].contains(Label))
				{
					break;
				}
				
			}
			
			if(lines[i].contains(value))
			{
				System.out.println("found");
				
			}
			
		} 
		catch (IOException e) 
		{	
			e.printStackTrace();
		}
		
	}*/
}
