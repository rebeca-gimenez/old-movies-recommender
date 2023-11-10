# Old Movies Recommender
A recommender code that takes as **input** 2 datasets (Movies and Ratings), a movie ID, and filters, and **returns** a list of similar films.
Includes basic testing code in JUnit.
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
1. Given a user with ratings on several movies (which should be in both datasets for better results).
2. A comparison score is computed where **raters with similar ratings get a higher score** than those with opposite ratings (computed as the dot product between two vectors).
3. Then a **weighted average is computed** for each movie (multiplying the dot product by the rating) and the movies with the highest scores are recommended to the user.<br>
Additional code needs to be written to avoid recommending movies the user has already seen.
