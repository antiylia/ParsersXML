package by.htp.doparsing.domain.entity;

public class Book {
	
	private int id;
	private String title;
	private int pages;
	private Author author;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String title, int pages, Author author) {
		super();
		this.title = title;
		this.pages = pages;
		this.author = author;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", pages=" + pages + ", author=" + author + "]";
	}
	
		
	
	
}
