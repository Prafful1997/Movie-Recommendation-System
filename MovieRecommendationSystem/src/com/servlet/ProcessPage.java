package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessPage")
public class ProcessPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HashMap<String,String>mp=FrontPage.getMap();
		EfficientRater user=new EfficientRater("1");
		String rating;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Double ratingValue;
		int totalRating=0;
		for(Map.Entry<String,String>entry:mp.entrySet())
		{
			rating=request.getParameter(entry.getKey());
			if(!rating.equals(""))
			{
				ratingValue=Double.valueOf(rating);
				if(ratingValue<1.0 || ratingValue >10.0)
				{
					out.println("<h2>Values must be integer in range(1-10)</h2>");
					return;
				}
				totalRating++;
				user.addRating(entry.getValue(),ratingValue);
			}
		}
		if(totalRating<10)
			out.println("<h2>Rate atleast 10 movies</h2>");
		else
		{
			ArrayList<Rating>list=UserAndRating.weightedAverageMovieRating(user);
			Movie movie;
			out.println("    <h2>List of Reccomend Movies</h2>");
			for(int i=0;i<list.size() && i<10;i++)
			{
				if(!user.hasRating(list.get(i).getMovieId()))
				{
					movie=MovieDatabase.getMovie(list.get(i).getMovieId());
					out.println("<h4>"+movie.getMovieName()+"</h4>");
				}
			}
		}
	}

}
