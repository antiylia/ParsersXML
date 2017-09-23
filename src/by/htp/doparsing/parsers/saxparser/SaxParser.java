package by.htp.doparsing.parsers.saxparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.doparsing.domain.entity.Book;

public class SaxParser {

	public static void main(String[] args) {

		BooksXMLHandler handler = new BooksXMLHandler();
		List<Book> result = parseBooksXML(handler);

		Iterator<Book> it = result.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	private static List<Book> parseBooksXML(DefaultHandler handler) {
		List<Book> listbook = new ArrayList<>();
		String fileName = "resources\\BooksXML.xml";

		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(fileName));
			listbook = ((BooksXMLHandler) handler).getListbook();

		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listbook;
	}

}
