package by.htp.doparsing.parsers.staxparser;

public enum BooksXMLTagName {
	BOOK, TITLE, PAGES, AUTHOR, NAME, SURNAME, AGE, BOOKS;

	public static BooksXMLTagName getElementTagName (String element) {
		switch (element) {
		case "book": 
			return BOOK;
		case "title": 
			return TITLE;
		case "pages": 
			return PAGES;
		case "author": 
			return AUTHOR;
		case "name": 
			return NAME;
		case "surname": 
			return SURNAME;
		case "age": 
			return AGE;
		case "books": 
			return BOOKS;
		default: throw new EnumConstantNotPresentException (BooksXMLTagName.class, element);	
		}
	}
}
