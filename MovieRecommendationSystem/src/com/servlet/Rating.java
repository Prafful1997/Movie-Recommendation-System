package com.servlet;

public class Rating {
    private Double rate;
    private String movieId;
    public Rating(String movieId,Double rate)
    {
            this.movieId=movieId;
            this.rate=rate;
    }
    public String getMovieId()
    {
            return movieId;
    }
    public Double getRate()
    {
            return rate;
    }        
}
