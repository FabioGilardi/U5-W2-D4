package FabioGilardi.U5W2D4.services;

import FabioGilardi.U5W2D4.dao.AuthorDAO;
import FabioGilardi.U5W2D4.entities.Author;
import FabioGilardi.U5W2D4.exceptions.BadRequestException;
import FabioGilardi.U5W2D4.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO ad;

    public Page<Author> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return ad.findAll(pageable);
    }

    public Author save(Author newAuthor) {
        if (!ad.existsByEmail(newAuthor.getEmail())) {
            newAuthor.setAvatar();
            ad.save(newAuthor);
            return newAuthor;
        } else throw new BadRequestException("This email: " + newAuthor.getEmail() + " already exists");

    }

    public Author findById(long authorId) {
        return ad.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndUpdate(long authorId, Author updatedAuthor) {
        Author found = findById(authorId);
        found.setName(updatedAuthor.getName());
        found.setSurname(updatedAuthor.getSurname());
        found.setEmail(updatedAuthor.getEmail());
        found.setBirthday(updatedAuthor.getBirthday());
        found.setAvatar();
        return found;
    }

    public void findByIdAndDelete(long authorId) {
        Author found = findById(authorId);
        ad.delete(found);
    }
}
