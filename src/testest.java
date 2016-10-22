import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;


public class testest {
	public static void main(String[] args) throws FileNotFoundException 
	{   
		System.out.println(new File(".").getAbsoluteFile());

	    File file=new File("BACK.JPG");
	    System.out.println(file.exists());
	    Scanner scan=new Scanner(file);
	
		
		/*
		ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		List<RecognitionResult> results =
		    clarifai.recognize(new RecognitionRequest(new File("kittens.jpg")));

		for (Tag tag : results.get(0).getTags()) {
		  System.out.println(tag.getName() + ": " + tag.getProbability());
		}
		
		*
		*
		*
		*/
		String APP_ID = "ORnpyh_pZSE46j9b9Hl06XcfIc8HN4OvJTBsnFeV",APP_SECRET = "8Qe7yvDFOQgqIQ04FTXWqJAFXjvz3sFZuiZ97Hno";
		ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		List<RecognitionResult> results =
		    clarifai.recognize(new RecognitionRequest(new File("BACK.JPG")));

		for (Tag tag : results.get(0).getTags()) {
		  System.out.println(tag.getName() + ": " + tag.getProbability());
		}
		/*
		File file2 = new File("BACK.jpg");
		System.out.println(file.canRead());*/

	}
}
