package s55_21870.t_21.salma_zidane.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping("/search")
    public Note searchNotes(@RequestParam String title) {

        return noteService.getAllNotes().stream()
                .filter(note -> note.getTitle()
                        .toLowerCase()
                        .contains(title.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No notes found with title containing: " + title
                ));
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/{id}")
    public Note deleteNote(@PathVariable String id) {
        Note deletedNote= noteService.getNoteById(id); // get it first (throws 404 if not found)
        noteService.deleteNote(id);
        return deletedNote; // returns 200 OK with deleted note
    }

}
