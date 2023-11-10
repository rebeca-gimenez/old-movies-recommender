package ratings;

public class Rating implements Comparable<Rating> {
	private String id;
    private double rating;
    
    public Rating (String aMovieId, double aRating) {
    	id = aMovieId;
    	rating = aRating;
    }
    public String toString() {
        return "ID: " + id + ", Rating: " + rating;
    }
    // Get methods
    public double getRating () {
        return rating;
    }
    public String getId () {
        return id;
    }

	@Override
	public int compareTo(Rating o) {
		// TODO Auto-generated method stub
		if (rating < o.rating) return -1;
        if (rating > o.rating) return 1;
		return 0;
	}

}
