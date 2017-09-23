package by.htp.doparsing.parsers.saxparser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.doparsing.domain.entity.Author;
import by.htp.doparsing.domain.entity.Book;

public class BooksXMLHandler extends DefaultHandler {

	private List<Book> listbook = new ArrayList<>();
	private Book book;
	private Author author;
	
	private StringBuilder text;

	public List<Book> getListbook() {
		return listbook;
	}
	
	public void startDocument() throws SAXException {
	    System.out.println("Parsing started");
	}
	public void endDocument() throws SAXException {
	    System.out.println("Parsing ended");
	}
			
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				
		 text = new StringBuilder();
		 if (qName.equals("book")) {
			 book = new Book();
			 int idFromXML = Integer.parseInt(attributes.getValue("id"));
			 book.setId((idFromXML));
		 }
		 if (qName.equals("author")) {
			 author = new Author();
			 }
	  }
	
    public void characters(char[] ch, int start, int length) {
    	String str = new String (ch, start, length);
    	str = str.trim();
    	if (!str.isEmpty()) {
    	text.append(str);
    	}
    }
	
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	setBookFields (qName);   
      }
        
    
	
   public void setBookFields (String qName) {
		 BookSXMLTagName tagName = BookSXMLTagName.valueOf(qName.toUpperCase().replace("-", "_"));
		 switch (tagName) {
		 case TITLE: 
			 book.setTitle(text.toString());
			 break;
		 case PAGES: 
			 book.setPages(Integer.parseInt(text.toString()));
			 break;
		 case AUTHOR: 
			 book.setAuthor(author);
			 break;
		 case BOOK: 
			listbook.add(book);	
			book = null;
			break;
		default:
			setAuthorFromXML(tagName);	
			break;
	     	}
		}
	 
	 private void setAuthorFromXML(BookSXMLTagName tagName) {
		switch (tagName) {
		 case NAME: 
			author.setName(text.toString());
			break;
		 case SURNAME: 
			 author.setSurname(text.toString());
			 break;
		 case AGE: 
			 author.setAge(Integer.parseInt(text.toString()));
			 break;
		 default:			 
		 	}
		}	 	
}
	
	