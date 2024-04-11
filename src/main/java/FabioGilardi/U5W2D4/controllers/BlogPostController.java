package FabioGilardi.U5W2D4.controllers;

import FabioGilardi.U5W2D4.entities.BlogPost;
import FabioGilardi.U5W2D4.exceptions.BadRequestException;
import FabioGilardi.U5W2D4.payload.BlogPostDTO;
import FabioGilardi.U5W2D4.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    private Page<BlogPost> getBlogPosts(@RequestParam(defaultValue = "0") int number, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.blogPostService.findAll(number, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost save(@RequestBody @Validated BlogPostDTO blogPostPayload, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return blogPostService.save(blogPostPayload);
    }

    @GetMapping("/{blogPostId}")
    private BlogPost findById(@PathVariable long blogPostId) {
        return blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    private BlogPost findByIdAndUpdate(@PathVariable long blogPostId, @RequestBody BlogPostDTO updatedBlogPost) {
        return blogPostService.findByIdAndUpdate(blogPostId, updatedBlogPost);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findByIdAndDelete(@PathVariable long blogPostId) {
        blogPostService.findByIdAndDelete(blogPostId);
    }

    @PostMapping("/upload/{blogPostId}")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable long blogPostId) throws IOException {
        return blogPostService.uploadImage(image, blogPostId);
    }
}