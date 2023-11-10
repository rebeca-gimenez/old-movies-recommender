package raters;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to store information about a rater's ratings.
 * A rater can rate more than 1 movie, but can rate a movie only once.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 * */
public class Rater {
	private String raterId;
	// ratingsMap is a HashMap containing a movieId String and a rating value
	private HashMap<String, Double> ratingsMap;
	
	public Rater(String aRaterId) {
		raterId = aRaterId;
		ratingsMap = new HashMap<String, Double>();
	}
	public Rater(String aRaterId, String aMovieId, double aRating) {
		raterId = aRaterId.trim();
		ratingsMap = new HashMap<String, Double>();
		addRating(aMovieId, aRating);
	}
	@Override
	public String toString() {
		return "Rater " + raterId + " has " + ratingsMap.size() + " rated movies";
	}
	
	public boolean addRating(String aMovieId, double aRating) {
		aMovieId = aMovieId.trim();
		while (aMovieId.length()<7) {
			aMovieId = "0" + aMovieId; 
		}
		if (ratingsMap.containsKey(aMovieId)) {
			return false;
		}
		ratingsMap.put(aMovieId, aRating);
		return true;
	}
	
	//Adding get methods
	public String getRaterId() {
		return raterId;
	}
	public int getNumRatedMovies() {
		return ratingsMap.size();
	}
	public boolean hasMovie(String movieId) {
		return ratingsMap.containsKey(movieId);
	}
	public double getMovieRating(String movieId) {
		return ratingsMap.get(movieId);
	}
	//Adding additional methods
	public ArrayList<String> getMovieIds() {
		ArrayList<String> list = new ArrayList<String>();
        for (String s : ratingsMap.keySet()) {
            list.add(s);
        }
		return list;
	}
}
