package com.techtalentsouth.techtalentblog.blogposts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //responds to web requests
public class BlogPostController {
	
	@Autowired 
	private BlogPostRepository blogPostRepository;
	
	private static List<BlogPost> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	public String getIndex (Model model) {
		posts.removeAll(posts);
		Iterable<BlogPost> iterable = blogPostRepository.findAll();
		for(BlogPost post : iterable) {
			posts.add(post);
		}
		
		//BlogPost blogPost = new BlogPost();
		//model.addAttribute("blogPost",blogPost);
		model.addAttribute("posts",posts);
		return "blogpost/index";
	}
	
	@GetMapping(value="/blogposts/new")
	public String newBlog(BlogPost post) {
		return "blogpost/new";
	}
	
	
	@PostMapping(value="/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost savedBlogPost = blogPostRepository.save(blogPost);
		posts.add(savedBlogPost);
		model.addAttribute("title", savedBlogPost.getTitle());
		model.addAttribute("author", savedBlogPost.getAuthor());
		model.addAttribute("blogEntry", savedBlogPost.getBlogEntry());
		return "blogpost/result";
	}
	
	@GetMapping(value="/blogposts/{id}")
	public String editPostWithId(@PathVariable Long id,Model model) {
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if(post.isPresent()) {
			BlogPost actualPost = post.get();
			model.addAttribute("blogPost",actualPost);
		}
		return "blogpost/edit";
	}
	
	@RequestMapping(value="/blogposts/update/{id}")
	public String updateExistingPost(@PathVariable Long id, BlogPost blogPost,Model model) {
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if(post.isPresent()) {
			BlogPost actualPost = post.get();
			actualPost.setTitle(blogPost.getTitle());
			actualPost.setAuthor(blogPost.getAuthor());
			actualPost.setBlogEntry(blogPost.getBlogEntry());
			blogPostRepository.save(actualPost);
			model.addAttribute("blogPost",actualPost);
		}
		return "blogpost/result";
	}
	
	@RequestMapping(value="/blogposts/delete/{id}")
	public String deletePostById(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
		return "blogpost/delete";
	}
}
