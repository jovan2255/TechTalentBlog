package com.techtalentsouth.TechTalentBlog.BlogPosts;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {
	
	@Autowired
private BlogPostsRepository blogPostsRepository;
private List<BlogPost> posts = new ArrayList<>();	
private BlogPost blogPosts;


@PostMapping(value = "/")
public String create(BlogPost blogPosts, Model model) {
	blogPostsRepository.save(blogPosts);
	model.addAttribute("posts", posts);
	model.addAttribute("title", blogPosts.getTitle());
	model.addAttribute("author", blogPosts.getAuthor());
	model.addAttribute("blogEntry", blogPosts.getBlogEntry());
	return "blogpost/result";
	}

	@GetMapping("/")
    public String index(BlogPost blogPosts, Model model) {
		model.addAttribute("posts", posts);
    	model.addAttribute("posts", blogPostsRepository.findAll());
    	return "blogposts/index";
    }
	@GetMapping("/new")
    public String news(BlogPost blogPosts, Model model) {
    	model.addAttribute("posts", blogPostsRepository.findAll());
    	return "blogposts/new";
    }
	

	@GetMapping("/Edit/{id}")
    public String update(@PathVariable Long id, Model model) {
		
		//Get the post
		Optional<BlogPost> result = blogPostsRepository.findById(id);
		BlogPost blogPost = null;
		if ( result.isPresent()) {
			blogPost = result.get();
		} else {
			throw new RuntimeException("Did not find post id - " + id );
		}
		
		
		
		

	//set the post as a model attribute
		
		
		model.addAttribute("blogPost", blogPost);
 
    	return "blogposts/new";
    }
	
	
	
	@GetMapping("/Delete/{id}")
    public String delete(@PathVariable Long id) {
		
		blogPostsRepository.deleteById(id);
		    	return "redirect:/";
    }
	
	
	 @PostMapping(value = "/results")
	    public String addNewBlogPost(BlogPost blogPosts, Model model) {
		blogPostsRepository.save(blogPosts);
		model.addAttribute("title", blogPosts.getTitle());
		model.addAttribute("author", blogPosts.getAuthor());
		model.addAttribute("blogEntry", blogPosts.getBlogEntry());
		return "blogposts/result";
	 }
	 
	 @GetMapping(value = "/results")
	 public String newBlog (BlogPost blogPost) {
	return "blogposts/result";
	 }


	 

}