# Old Movies Recommender
Code that takes as **input** 2 datasets (Movies and Ratings), a rater ID, and filters, and **returns** a list of recommended films for that rater, based on the ratings of other users.<br>
Includes basic testing code in JUnit.<br>
The method getSimilarRatings and getSimilarRatingsWithFilter from the ratings.java file contains the code for the recommendations.
## Input
### Datasets
1. Movies: contains the following information: movie ID, title,	year,	country,	genre,	director,	minutes, and	poster URL.
2. Ratings: contains the following information: rater ID, movie ID,  rating value,	and time of rating.
Examples of these datasets can be found in the [data folder](https://github.com/rebeca-gimenez/old-movies-recommender/tree/main/OldMoviesRecommender/data)
### Other input
1. A movie ID (must be in the Ratings dataset).
2. Filters: the movie recommendations can be filtered by genre, year, duration (minutes), director, or any combination of these.
## Code
The code is based on a similar rating score:
1. Take as input a "user" (rater) with ratings on several movies. The user needs to be in the Ratings dataset.
2. A comparison score is computed where **raters with similar ratings get a higher score** than those with opposite ratings (computed as the dot product between two vectors).
3. Then a **weighted average is computed** for each movie (multiplying the dot product by the rating) and the movies with the highest scores are recommended to the user.<br>
## Next steps
Additional code needs to be written to avoid recommending movies the user has already seen. This can easily be achieved by removing from the output the movies contained in the user ratings map.
