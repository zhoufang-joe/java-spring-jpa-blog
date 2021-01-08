package com.pluralsight.blog;

import com.pluralsight.blog.data.CategoryRepository;
import com.pluralsight.blog.data.PostRepository;
import com.pluralsight.blog.model.Category;
import com.pluralsight.blog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;

    public BlogController(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String listPosts(ModelMap modelMap) {
        List<Post> posts = postRepository.findAll();
        modelMap.put("posts", posts);
        List<Category> categories = categoryRepository.findAll();
        modelMap.put("categories", categories);
        return "home";
    }

    @RequestMapping("/post/{id}")
    public String postDetails(@PathVariable Long id, ModelMap modelMap) {
        Post post = postRepository.findById(id).orElse(null);
        modelMap.put("post", post);
        return "post-details";
    }

    @RequestMapping("/category/{id}")
    public String categoryList(@PathVariable Long id, ModelMap modelMap) {
        Category category = categoryRepository.findById(id).orElse(null);
        modelMap.put("category", category);
        List<Post> posts = postRepository.findByCategory(category);
        modelMap.put("posts", posts);
        List<Category> categories = categoryRepository.findAll();
        modelMap.put("categories", categories);
        return "category-list";
    }
}
