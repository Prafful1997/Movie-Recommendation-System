package com.servlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class EfficientRater implements Rater
{
        private String myId;
        private HashMap<String,Rating>myRating;
        public EfficientRater(String myId)
        {
                this.myId=myId;
                myRating=new HashMap<String,Rating>();
        }
        public String getMyId()
        {
                return myId;
        }
        @Override
        public boolean hasRating(String movieId) 
        {
                if(myRating.containsKey(movieId))
                        return true;
                else
                        return false;
        }
        @Override
        public Double getRating(String movieId) 
        {
                if(myRating.containsKey(movieId))
                        return myRating.get(movieId).getRate();
                else
                        return 0.0;
        }
        @Override
        public void addRating(String movieId,Double rating) 
        {
                myRating.put(movieId,new Rating(movieId,rating));
        }
        @Override
        public ArrayList<Rating> getMovieRated() 
        {
                ArrayList<Rating>l=new ArrayList<Rating>();
                for(Map.Entry<String,Rating>entry:myRating.entrySet())
                        l.add(new Rating(entry.getValue().getMovieId(),entry.getValue().getRate()));
                return l;
        }       
}
