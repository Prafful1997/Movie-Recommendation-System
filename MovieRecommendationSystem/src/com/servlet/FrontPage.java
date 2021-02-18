package com.servlet;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/FrontPage")
public class FrontPage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String>mp;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{System.out.println(System.getProperty("java.runtime.version"));
	
		MovieDatabase.loadMovieData("F:\\MovieRecommendationSystem\\ratedmoviesfull.csv");
		RaterDatabase.loadRatingData("F:\\MovieRecommendationSystem\\ratings.csv");
		ArrayList<Rating>list=UserAndRating.averageMovieRatingAllRaters();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>");
		out.println("Movie Recommendation");
		out.println("</title></head>");
		out.println("<body>");
		out.println("<form action='ProcessPage' method='post'><input type='submit'>");
		Movie movie;
		int i=1;
		if(mp==null)
			mp=new HashMap<String,String>();
		for(Rating movieId:list)
		{
			movie=MovieDatabase.getMovie(movieId.getMovieId());
			out.println("<h2>"+movie.getMovieName()+" <input type='text' name='r"+String.valueOf(i)+"'>");
			mp.put("r"+String.valueOf(i),movieId.getMovieId());
			i++;
		}
		System.out.println(i);
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	public static HashMap<String,String>getMap()
	{
		return mp;
	}
}
