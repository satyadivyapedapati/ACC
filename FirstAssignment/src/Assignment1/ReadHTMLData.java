/**
 * Author: veera venkata satya divya pedapati
 * Assigment 1- Taskone
 */
package Assignment1;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static Assignment1.DocumentTextExtractionMode.REGEX;
public class ReadHTMLData {
	
//Method to return lowercase string which contains 0-9a-zA-Z characters
	 public static String sanitizeString(String data) 
	 	{
	        if (data.matches("[0-9a-zA-Z]+")) return data.toLowerCase();  //converts string to lowercase
	        return "";
	    }
	 
// Method to clean the string using jsoup
	    public static String cleanDocumentViaJsoup(String data) 
	    {
	        return Jsoup.parse(data).text();
	    }

//Method to clean using regex
	    public static String cleanDocumentViaRegex(String data) 
	    {
	        return data
	                .replaceAll("<(.|\f|\r|\n)*?>", " ")  //return plain string by replaces spaces,newlines,breaks with space
	                .replaceAll("\\s+", " ");
	    }
	    
	    
//method to read html files from given filepath
		public static String readHtmlFile(String filePath) 
		{
	        StringBuilder htmlFileData = new StringBuilder();//string builder to store the html file data
	        try (BufferedReader bRHtml = new BufferedReader(new FileReader(filePath))) 
	        {//Creates buffered reader to read the lines in file
	            String line;
	            while ((line = bRHtml.readLine()) != null) 
	            {
	                line = line.trim();
	                if (!line.isEmpty()) htmlFileData.append(line);
	            }
	        } 
	        catch (IOException e) 
	        {
	            System.out.printf("Encountered error while reading the file at '%s': '%s'%n", filePath, e);
	        }
	        return htmlFileData.toString();//converting string builder to string
	    }
		
		
		//main method
		public static void main(String args[])
		{
		
		}

		 

}
