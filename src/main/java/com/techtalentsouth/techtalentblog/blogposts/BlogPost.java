package com.techtalentsouth.techtalentblog.blogposts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //used when trying to store in java persistence database
public class BlogPost {
	
	//Id labels next variable as primary key when storing in db
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; //when use double?
	
	private String title;
	private String author;
	private String blogEntry;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}

	public Long getId() {
		return id;
	}

	public BlogPost() {
		//no arg constructor
	}

	public BlogPost(String title, String author, String blogEntry) {
		super();
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
	}

	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry + "]";
	}
	
	
	
}
