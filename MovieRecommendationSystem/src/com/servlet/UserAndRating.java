package com.servlet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class UserAndRating {
	public static Double dotProduct(EfficientRater me,EfficientRater rater)
	{
		Double dotValue=0.0;
		ArrayList<Rating>myRating=me.getMovieRated();
		for(Rating rating:myRating)
		{
			if(rater.hasRating(rating.getMovieId()))
				dotValue+=((rating.getRate()-5)*(rater.getRating(rating.getMovieId())-5));
		}
		return dotValue;
	}
	public static ArrayList<Rating> averageMovieRatingAllRaters()
	{
		ArrayList<Rating> list=new ArrayList<Rating>();
		ArrayList<String>movieList=MovieDatabase.getAllMovies();
		ArrayList<EfficientRater>allRater=RaterDatabase.getAllRater();
		String movieId;
		Double avgVal,val;
		int totalRater;
		for(int i=0;i<movieList.size();i++)
		{
			avgVal=0.0;
			movieId=movieList.get(i);
			totalRater=0;
			for(EfficientRater rater:allRater)
			{
				val=rater.getRating(movieId);
				if(val>0.0) 
				{
					totalRater++;
					avgVal+=val;
				}
			}
			list.add(new Rating(movieId,avgVal/totalRater));
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
	public static ArrayList<Rating> weightedAverageMovieRating(EfficientRater user)
	{
		ArrayList<Rating>list=new ArrayList<Rating>();
		ArrayList<String>movieList=MovieDatabase.getAllMovies();
		ArrayList<Rating>similarRaters=RaterDatabase.getSimilarRaters(user);
		String currMovie;
		Double weight;
		for(int i=0;i<movieList.size();i++)
		{
			weight=0.0;
			currMovie=movieList.get(i);
			for(Rating rater:similarRaters)
			{
				EfficientRater raterObj=RaterDatabase.getRater(rater.getMovieId());
				if(raterObj.hasRating(currMovie))
				{
					weight+=(rater.getRate()*raterObj.getRating(currMovie));
				}
			}
			list.add(new Rating(currMovie,weight));
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