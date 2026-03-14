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

    public List<Note> findAll(){
        return notes;
    }

    public Optional<Note> findById(String id){
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    public List<Note> findByUserId(String userId) {
        return notes.stream()
                .filter(note -> note.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Note> findByTitle(String title){
        return notes.stream()  // assuming `notes` is your in-memory list
                .filter(note -> note.getTitle() != null
                        && note.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Note save(Note note) {
        Note newNote = new Note(note.getTitle(), note.getContent(), note.getUserId());
        notes.add(newNote);
        new ObjectMapper().writeValue(jsonFile, notes);
        return newNote;
    }

    public Optional<Note> update(String id, Note updated) {
        Optional<Note> targetNote = notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();

        targetNote.ifPresent(note -> {
            note.setTitle(updated.getTitle());
            note.setContent(updated.getContent());
            note.setUserId(updated.getUserId());
        });

        new ObjectMapper().writeValue(jsonFile, notes);

        return targetNote;
    }

    public boolean deleteById(String id) {
        Optional<Note> targetNote = notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();

        if (targetNote.isPresent()) {
            notes.remove(targetNote.get());
            new ObjectMapper().writeValue(jsonFile, notes);
            return true;
        }
        return false;
    }


}
