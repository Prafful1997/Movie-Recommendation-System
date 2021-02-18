package com.servlet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//import org.apache.commons.csv.CSVParser;

//import au.com.bytecode.opencsv.*;
import java.nio.file.Paths;
import java.nio.file.Files;
public class MovieDatabase 
{
	private static HashMap<String,Movie>myMovie;
	public static void loadMovieData(String filePath)
	{
		if(myMovie==null)	
			myMovie=new HashMap<String,Movie>();
		try
		{
			//Reader reader = Files.newBufferedReader(Paths.get(filePath));
            //CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			//FileReader fileReader=new FileReader(filePath);
			//String[] record=null;
			//CSVReader csvReader=new CSVReader(fileReader);
			//while((record=csvReader.readNext())!=null)
			//for (CSVRecord record : csvParser)
			String line = "";  
			String splitBy = ",";  
            BufferedReader br = new BufferedReader(new FileReader(filePath)); 
            while ((line = br.readLine()) != null)  
			{
            	String[] record = line.split(splitBy);
				Movie movie=new Movie(record[0],record[1],record[4],Integer.valueOf(record[2]));
				myMovie.put(record[0],movie);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static ArrayList<String>getAllMovies()
	{
		ArrayList<String>list=new ArrayList<String>();
		for(Map.Entry<String,Movie>entry:myMovie.entrySet())
			list.add(entry.getKey());
		return list;
	}
	public static Movie getMovie(String movieId)
	{
		return myMovie.get(movieId);
	}
}

