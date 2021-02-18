package com.servlet;
import java.util.ArrayList;
public interface Rater {
        boolean hasRating(String movieId);
        Double getRating(String movieId);
        void addRating(String movieId,Double rating);
        ArrayList<Rating> getMovieRated();
}
