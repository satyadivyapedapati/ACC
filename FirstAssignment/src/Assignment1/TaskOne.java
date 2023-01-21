/**
 * Author: veera venkata satya divya pedapati
 * Assigment 1- Taskone
 */
package Assignment1;
import java.util.Hashtable;
import java.io.File;

import static Assignment1.DocumentTextExtractionMode.REGEX;

public class TaskOne 
{
	//class variables
	public static ReadHTMLData rhd= new ReadHTMLData();
	final static Hashtable<String, Long> keyAndCountTable = new Hashtable<>(); //Creating static hash table to create frequency and count of words in all html files in specific path
	
	//class methods
    private static Hashtable<String, Long> computeWordFrequencyFromHtmlFile(String htmlFileLocation, DocumentTextExtractionMode mode) //Method to compute word count frequency
    {
        System.out.println("Going to read html file from location: " + htmlFileLocation);
        String htmlData =rhd.readHtmlFile(htmlFileLocation); // calling readHtmlFile method to read data in htmlfile
        System.out.println("HTML file read complete, initiating first cleaning operation.");
        String cleanHtmlData;
        switch (mode) //cleaning data based on the mode passed onto
        { 
            case REGEX:
                cleanHtmlData = rhd.cleanDocumentViaRegex(htmlData);
                break;
            case JSOUP:
            default:
                cleanHtmlData = rhd.cleanDocumentViaJsoup(htmlData);
        }
        //System.out.println("Cleaned data-------\n"+cleanHtmlData);
        String[] splitHtmlData = cleanHtmlData.split(" ");
        for (int i = 0; i < splitHtmlData.length; i++) //iterate over the words from the string array and augment count of the hashtable
        { 
            String key = rhd.sanitizeString(splitHtmlData[i]);
            if(keyAndCountTable.containsKey(key)) //if key already exists in hashtable increment its value by 1
            {
            	Long newValue=keyAndCountTable.get(key)+1;
            	keyAndCountTable.put(key,newValue);
            }
            else //create new key, value as 1
            	keyAndCountTable.put(key,(long) 1);
        }
        System.out.println("Sanitization complete. Completed generating the word frequency hash-table.");
        return keyAndCountTable; //return the populated hashtable
    }

//main method
    public static void main(String[] args) 
	{
		
		try {//using exceptional handling to avoid program hault
			File directoryPath = new File("C:/Users/shiva/git/ACC/FirstAssignment/Resource/W3C Web Pages");//setting up file path to access files
			  File filesList[] = directoryPath.listFiles();//get list of files and store it in an arrya
			  for(File file : filesList) //Iterating loop to access every file in specified path
			  {
				  computeWordFrequencyFromHtmlFile(file.getAbsolutePath(), REGEX);  //mthod call to compute word and frequencies
			  }
   
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------\n");
		System.out.println(keyAndCountTable);
		System.out.println("---------------------------\n");
	}
		
    

}
