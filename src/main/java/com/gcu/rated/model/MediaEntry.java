package com.gcu.rated.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Holds one movie, TV show, or music album from the user's library.
@Entity
@Table(name = "media_entries")
public class MediaEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long mediaId;

    @NotBlank(message = "Please enter a title.")
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Please choose a media type.")
    @Column(name = "media_type", nullable = false, length = 30)
    private String mediaType;

    @Column(name = "creator", length = 150)
    private String creator;

    @Column(name = "release_year")
    private Integer releaseYear;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_consumed")
    private LocalDate dateConsumed;

    @NotNull(message = "Please enter a rating.")
    @Min(value = 1, message = "The rating must be at least 1.")
    @Max(value = 10, message = "The rating cannot be higher than 10.")
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    public Long getMediaId() { return mediaId; }
    public void setMediaId(Long mediaId) { this.mediaId = mediaId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public LocalDate getDateConsumed() { return dateConsumed; }
    public void setDateConsumed(LocalDate dateConsumed) { this.dateConsumed = dateConsumed; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}
