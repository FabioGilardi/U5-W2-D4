package FabioGilardi.U5W2D4.controllers;


import FabioGilardi.U5W2D4.entities.Author;
import FabioGilardi.U5W2D4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    private Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return authorService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    private Author postAuthor(@RequestBody Author newAuthor) {
        return authorService.save(newAuthor);
    }

    @GetMapping("/{authorId}")
    private Author getAuthorById(@PathVariable long authorId) {
        return authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    private Author putAuthorById(@PathVariable long authorId, @RequestBody Author updatedAuthor) {
        return authorService.findByIdAndUpdate(authorId, updatedAuthor);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    private void deleteAuthor(@PathVariable long authorId) {
        authorService.findByIdAndDelete(authorId);
    }
}
