package com.gcu.rated.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gcu.rated.model.MediaEntry;

// Spring Data JPA handles the database queries for media entries.
public interface MediaEntryRepository extends JpaRepository<MediaEntry, Long> {
    List<MediaEntry> findByMediaTypeOrderByMediaIdDesc(String mediaType);
}
