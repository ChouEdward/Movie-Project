package com.moviesapp.project.dm_project.data;

import android.support.annotation.NonNull;

public class ModuleAddressBean implements Comparable<ModuleAddressBean>{


    public ModuleAddressBean(String adult, String belongs_to_collection, String budget, String genres, String homepage, Long id, String imdb_id, String original_language, String original_title, String overview, String popularity, String poster_path, String production_companies, String production_countries, String release_date, String revenue, String runtime, String spoken_languages, String status, String tagline, String title, String video, String vote_average, String vote_count) {
        this.adult = adult;
        this.belongsToCollection = belongs_to_collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdb_id;
        this.originalLanguage = original_language;
        this.originalTitle = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = poster_path;
        this.productionCompanies = production_companies;
        this.productionCountries = production_countries;
        this.releaseDate = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = vote_average;
        this.voteCount = vote_count;
    }

    private Long id;

    private String title;

    private String overview;

    private String adult;

    private String belongsToCollection;

    private String budget;

    private String genres;

    private String homepage;

    private String imdbId;

    private String originalLanguage;

    private String originalTitle;

    private String popularity;

    private String posterPath;

    private String productionCompanies;

    private String productionCountries;

    private String releaseDate;

    private String revenue;

    private String runtime;

    private String spokenLanguages;

    private String status;

    private String tagline;

    private String video;

    private String voteAverage;

    private String voteCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult == null ? null : adult.trim();
    }

    public String getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(String belongsToCollection) {
        this.belongsToCollection = belongsToCollection == null ? null : belongsToCollection.trim();
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget == null ? null : budget.trim();
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres == null ? null : genres.trim();
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage == null ? null : homepage.trim();
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId == null ? null : imdbId.trim();
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage == null ? null : originalLanguage.trim();
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle == null ? null : originalTitle.trim();
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity == null ? null : popularity.trim();
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath == null ? null : posterPath.trim();
    }

    public String getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies == null ? null : productionCompanies.trim();
    }

    public String getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries == null ? null : productionCountries.trim();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue == null ? null : revenue.trim();
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages == null ? null : spokenLanguages.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline == null ? null : tagline.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage == null ? null : voteAverage.trim();
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount == null ? null : voteCount.trim();
    }

    public ModuleAddressBean(String overview) {
        this.overview = overview;
    }

    @Override
    public int compareTo(@NonNull ModuleAddressBean o) {
        int i= 0;
        try{
            double differ = Double.parseDouble(this.getVoteAverage())-
                    Double.parseDouble(o.getVoteAverage());
            if(differ > 0){
                i=1;
            }else if(differ == 0.0){
                double differ_po = Double.parseDouble(this.getPopularity())-
                        Double.parseDouble(o.getPopularity());
                return differ_po>=0.0?1:-1;
            }else{
                i=-1;
            }
        }catch (Exception e){

        }
        return i;
    }
}
