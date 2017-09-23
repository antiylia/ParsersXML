package by.htp.doparsing.parsers.staxparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.doparsing.domain.entity.Author;
import by.htp.doparsing.domain.entity.Book;

public class StaxParser {

	public static void main(String[] args) {
		parseXML();
	}

	public static void parseXML() {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			String fileName = "resources\\BooksXML.xml";
			InputStream input = new FileInputStream(fileName);

			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List<Book> listbok = getDataFromXML(reader);

			Iterator<Book> it = listbok.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}

		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (XMLStreamException e) {
			e.getMessage();
		}
	}

	
	private static List<Book> getDataFromXML(XMLStreamReader reader) throws XMLStreamException {
		List<Book> listbook = new ArrayList<Book>();
		Book book = null;
		Author author = null;
		BooksXMLTagName element = null;
		while (reader.hasNext()) {
			int type = reader.next();

	  switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				element = BooksXMLTagName.getElementTagName(reader.getLocalName());
				book = startElementBook(reader, element, book);

				if (element.equals(BooksXMLTagName.AUTHOR)) {
					author = new Author();
				}

				break;
			case XMLStreamConstants.CHARACTERS:
				book = charactersBook(reader, element, book, author);
				break;
				
			case XMLStreamConstants.END_ELEMENT:
				element = BooksXMLTagName.getElementTagName(reader.getLocalName());
				listbook = endElementBook(reader, listbook, book);
				break;
			}
		}
		return listbook;
	}
	
	
	
	private static Book startElementBook(XMLStreamReader reader, BooksXMLTagName element, Book book) {
		if (element.equals(BooksXMLTagName.BOOK)) {
			book = new Book();
			int id = Integer.parseInt(reader.getAttributeValue(null, "id"));
			book.setId(id);
		}
		return book;
	}

	private static Book charactersBook(XMLStreamReader reader, BooksXMLTagName element, Book book, Author author) {

		String text = reader.getText().trim();

		if (text.isEmpty()) {
			return book;
		}

		switch (element) {
		case TITLE:
			book.setTitle(text);
			break;
		case PAGES:
			book.setPages(Integer.parseInt(text));
			break;
		default:
			author = setAuthorFields(reader, element, text, author);
			book.setAuthor(author);
			break;
		}
		return book;
	}

	private static Author setAuthorFields(XMLStreamReader reader, BooksXMLTagName element, String text, Author author) {
		switch (element) {
		case NAME:
			author.setName(text);
			break;
		case SURNAME:
			author.setSurname(text);
			break;
		case AGE:
			author.setAge(Integer.parseInt(text));
			break;
		default:
			break;
		}
		return author;
	}

	private static List<Book> endElementBook(XMLStreamReader reader, List<Book> listbook, Book book) {
		BooksXMLTagName element = BooksXMLTagName.getElementTagName(reader.getLocalName());
		if (element.equals(BooksXMLTagName.BOOK)) {
			listbook.add(book);
		}
		return listbook;
	}

}