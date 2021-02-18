package com.servlet;
public class Movie 
{
        private String movieName,country,genre,director,movieId;
        private Integer minutes,year;
        public Movie(String movieId,String movieName,String genre,Integer year)
        {
                this.movieId=movieId;
                this.movieName=movieName;
                this.genre=genre;
                this.year=year;
        }
        public Movie(String movieName,String country,String genre,String director,String movieId,Integer minutes,Integer year)
        {
                this.movieName=movieName;
                this.country=country;
                this.genre=genre;
                this.director=director;
                this.movieId=movieId;
                this.minutes=minutes;
                this.year=year;
        }
        public String getMovieName()
        {
                return movieName;
        }
        public String getCountry()
        {
                return country;
        }
        public String getGenre()
        {
                return genre;
        }
        public String getDirector()
        {
                return director;
        }
        public String getMovieId()
        {
                return movieId;               
        }
        public Integer getMinutes()
        {
                return minutes;
        }
        public Integer getYear()
        {
                return year;
        }
}

