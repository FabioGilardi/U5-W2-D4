package FabioGilardi.U5W2D4.services;

import FabioGilardi.U5W2D4.dao.BlogPostDAO;
import FabioGilardi.U5W2D4.entities.BlogPost;
import FabioGilardi.U5W2D4.exceptions.NotFoundException;
import FabioGilardi.U5W2D4.payload.BlogPostDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostDAO bpd;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<BlogPost> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.bpd.findAll(pageable);
    }

    public BlogPost findById(long blogPostId) {
        return this.bpd.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost save(BlogPostDTO blogPostPayload) {
        return this.bpd.save(new BlogPost(blogPostPayload.blogType(), blogPostPayload.title(), blogPostPayload.content(), blogPostPayload.cover(), blogPostPayload.readingTime(), authorService.findById(blogPostPayload.authorId())));
    }


    public BlogPost findByIdAndUpdate(long blogPostId, BlogPostDTO updatedBlogPost) {
        BlogPost found = findById(blogPostId);
        found.setBlogType(updatedBlogPost.blogType());
        found.setTitle(updatedBlogPost.title());
        found.setCover(updatedBlogPost.cover());
        found.setContent(updatedBlogPost.content());
        found.setReadingTime(updatedBlogPost.readingTime());
        return this.bpd.save(found);
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = findById(blogPostId);
        this.bpd.delete(found);
    }

    public String uploadImage(MultipartFile img, long blogPostId) throws IOException {
        BlogPost found = findById(blogPostId);
        String url = (String) cloudinaryUploader.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setCover(url);
        bpd.save(found);
        return url;
    }
}
