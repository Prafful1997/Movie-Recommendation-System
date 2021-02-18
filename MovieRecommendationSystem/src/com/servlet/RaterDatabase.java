package com.servlet;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//import au.com.bytecode.opencsv.*;

public class RaterDatabase 
{
        private static HashMap<String,EfficientRater>myRater;
        public static void loadRatingData(String filePath) 
        {
        	if(myRater==null)
        	myRater=new HashMap<String,EfficientRater>();	
                try
                {
                	//FileReader fileReader=new FileReader(filePath);
                    //CSVReader csvReader=new CSVReader(fileReader);
                    //String[] record=null;
                	//Reader reader = Files.newBufferedReader(Paths.get(filePath));
                    //CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                    //while((record=csvReader.readNext())!=null)
                    //for (CSVRecord record : csvParser)
                	String line = "";  
        			String splitBy = ",";  
                    BufferedReader br = new BufferedReader(new FileReader(filePath));  
                    while ((line = br.readLine()) != null)  
                    {
                    	String[] record = line.split(splitBy); 
                     	if(myRater.containsKey(record[0]))
                        {
                     		myRater.get(record[0]).addRating(record[1],Double.valueOf(record[2]));
                        }
                        else
                        {
                       		myRater.put(record[0],new EfficientRater(record[0]));
                       		myRater.get(record[0]).addRating(record[1],Double.valueOf(record[2]));
                       	}
                    }
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
        }
        public static EfficientRater getRater(String myId)
        {
        	return myRater.get(myId);	
        }
        public static ArrayList<EfficientRater>getAllRater()
        {
        	ArrayList<EfficientRater>list=new ArrayList<EfficientRater>();
        	for(Map.Entry<String,EfficientRater>entry:myRater.entrySet())
        		list.add(entry.getValue());
        	return list;
        }
        public static ArrayList<Rating>getSimilarRaters(EfficientRater user)
        {
        	ArrayList<Rating>list=new ArrayList<Rating>();
        	ArrayList<EfficientRater>allRater=getAllRater();
        	Double dotValue;
        	for(EfficientRater rater:allRater)
        	{
        		if(!rater.getMyId().equals(user.getMyId()))
        		{
        			dotValue=UserAndRating.dotProduct(user,rater);
        			if(dotValue>=0)
        				list.add(new Rating(rater.getMyId(),dotValue));
        		}
        	}
        	Collections.sort(list,new Comparator<Rating>()
        	{
        		public int compare(Rating r1,Rating r2)
        		{
        			return Double.valueOf(r2.getRate()).compareTo(r1.getRate());
        		}
        	});
        	return list;
        }
}