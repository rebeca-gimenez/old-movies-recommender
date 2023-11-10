package movies;
/**
 * Movie is a Plain Old Java Object (POJO) class.
 * It stores all the available data for a movie (using the dataset as reference).
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class Movie {
	private String id;
	private String title;
	private int year;
	private String country;
	private String genres;
	private String director;
	private int minutes;
	private String poster;
	
	//A movie, needs to have at least an ID, title, year and genres
	public Movie (String theId, String theTitle, String theYear, String theGenres) {
		id = theId.trim();
		title = theTitle.trim();
		year = Integer.parseInt(theYear.trim());
		genres = theGenres.trim();
	}
	//If the movie has data for all the fields
	public Movie (String theId, String theTitle, String theYear, String theCountry, 
			String theGenres, String theDirector, String theMinutes, String thePoster) {
		id = theId.trim();
		title = theTitle.trim();
		year = Integer.parseInt(theYear.trim());
		country = theCountry.trim();
		genres = theGenres.trim();
		director = theDirector.trim();
		minutes = Integer.parseInt(theMinutes.trim());
		poster = thePoster.trim();
	}
	@Override
	public String toString() {
		return title + " (" + year + ") " + " is a " + genres + " movie";
	}
	//Adding get methods
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public int getYear() {
		return year;
	}
	public String getCountry() {
		return country;
	}
	public String getGenres() {
		return genres;
	}
	public String getDirector() {
		return director;
	}
	public int getMinutes() {
		return minutes;
	}
	public String getPoster() {
		return poster;
	}
}
