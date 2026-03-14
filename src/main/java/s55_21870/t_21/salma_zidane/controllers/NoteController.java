package s55_21870.t_21.salma_zidane.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import s55_21870.t_21.salma_zidane.models.Note;
import s55_21870.t_21.salma_zidane.services.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    // 1. GET /notes — returns all notes
    @GetMapping
    public List<Note> getAllUsers() {
        return noteService.getAllNotes();
    }

    // 2. GET /notes/{id} — returns a single note by id
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }


    // 3. POST /notes — create a new note
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    // 4. GET /notes/search?title=... — case-insensitive search by title
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String title) {
        return noteService.getNotesTitle(title);
    }

    // 5. PUT /notes/{id} — update a note
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note updatedNote) {
        return noteService.updateNote(id, updatedNote);
    }

    // 6. DELETE /notes/{id} — delete a note
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }

}
