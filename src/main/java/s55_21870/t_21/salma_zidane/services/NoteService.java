package s55_21870.t_21.salma_zidane.services;


import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import s55_21870.t_21.salma_zidane.models.Note;
import s55_21870.t_21.salma_zidane.repositories.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    }

    public List<Note> getNotesByUserId(String userId){
        List<Note> notes = noteRepository.findByUserId(userId);
        if (notes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Note not found for this User " + userId
            );
        }
        return notes;
    }

    public List<Note> getNotesTitle(String title) {
        List<Note> notes = noteRepository.findByTitle(title);

        if (notes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No notes found with title containing: " + title
            );
        }

        return notes;
    }

    public Note createNote(Note note){
        return noteRepository.save(note);
    }

    public Note updateNote(String id, Note note){
        return noteRepository.update(id, note)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    }

    public void deleteNote(String id) {
        if (!noteRepository.deleteById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        else
            System.out.println("Note with ID =" + id + " was deleted successfully!");
    }

}
