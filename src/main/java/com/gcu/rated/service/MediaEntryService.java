package com.gcu.rated.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gcu.rated.model.MediaEntry;
import com.gcu.rated.repository.MediaEntryRepository;

// Keeps the app's database work separate from the controller.
@Service
public class MediaEntryService {
    private final MediaEntryRepository repository;

    public MediaEntryService(MediaEntryRepository repository) {
        this.repository = repository;
    }

    // Shows every entry, or only entries from the selected category.
    public List<MediaEntry> findEntries(String mediaType) {
        if (mediaType == null || mediaType.isBlank() || mediaType.equals("All")) {
            return repository.findAll(Sort.by(Sort.Direction.DESC, "mediaId"));
        }
        return repository.findByMediaTypeOrderByMediaIdDesc(mediaType);
    }

    public MediaEntry findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    public void save(MediaEntry entry) {
        repository.save(entry);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        }
        repository.deleteById(id);
    }
}
