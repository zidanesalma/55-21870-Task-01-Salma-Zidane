package s55_21870.t_21.salma_zidane.repositories;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import s55_21870.t_21.salma_zidane.models.Note;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class NoteRepository {
    private List<Note> notes;
    private java.io.File jsonFile;


    public NoteRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/notes.json");
        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read notes.json");
        }
        try {
            this.jsonFile = new java.io.File(getClass().getResource("/notes.json").toURI());
        } catch (Exception e) {
            this.jsonFile = new java.io.File("/data/notes.json");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        this.notes = objectMapper.readValue(inputStream, new TypeReference<List<Note>>()
        {});
    }
    public List<Note> findAll() { return notes; }

    public Optional<Note> findById(String id) {
        return notes.stream().filter(n -> n.getId().equals(id)).findFirst();
    }

    public List<Note> findByUserId(String userId) {
        return notes.stream().filter(n -> userId.equals(n.getUserId())).collect(Collectors.toList());
    }

    public Note save(Note note) {
        note.setId(UUID.randomUUID().toString());
        notes.add(note);
        try { new ObjectMapper().writeValue(jsonFile, notes); } catch (Exception e) { e.printStackTrace(); }
        return note;
    }

    public Optional<Note> update(String id, Note updated) {
        Optional<Note> existing = findById(id);
        if (existing.isPresent()) {
            Note n = existing.get();
            n.setTitle(updated.getTitle());
            n.setContent(updated.getContent());
            n.setUserId(updated.getUserId());
            try { new ObjectMapper().writeValue(jsonFile, notes); } catch (Exception e) { e.printStackTrace(); }
        }
        return existing;
    }

    public boolean deleteById(String id) {
        boolean removed = notes.removeIf(n -> n.getId().equals(id));
        if (removed) {
            try { new ObjectMapper().writeValue(jsonFile, notes); } catch (Exception e) { e.printStackTrace(); }
        }
        return removed;
    }
}

