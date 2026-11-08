
-- Create the Rated database
CREATE DATABASE IF NOT EXISTS rated;
USE rated;

-- Store movies, TV shows, and albums
CREATE TABLE IF NOT EXISTS media_entries (
    media_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    media_type VARCHAR(30) NOT NULL,
    creator VARCHAR(150),
    release_year INT,
    date_consumed DATE,
    rating INT NOT NULL,
    review TEXT
);