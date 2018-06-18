package com.brillicaservices.gurjas.firebasemoviessample.series;

public class SeriesModelView {
    String title;
    int releaseYear;
    String description;
    int rating;
    int image;

    public SeriesModelView(String title, int image, int releaseYear, int rating, String description) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.image = image;
    }

    public SeriesModelView() {
        title="";
        releaseYear=0;
        description="";
        rating=0;
        image=0;
    }

    public int getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
//    implement all the details as that for MoviesModelView

