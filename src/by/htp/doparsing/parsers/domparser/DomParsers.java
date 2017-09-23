package by.htp.doparsing.parsers.domparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.doparsing.domain.entity.Author;
import by.htp.doparsing.domain.entity.Book;

public class DomParsers {

	public static void main(String[] args) {
		doDomParsing();
	}

	private static void doDomParsing() {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse("resources\\BooksXML.xml");
			Node root = document.getDocumentElement();
			
			List<Book> list = new ArrayList<>();
			Book book = new Book();
			Author author = new Author();
			book.setAuthor(author);
			getElement(root, book, author);
			
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void getElement(Node root, Book book, Author author) {
		List<Book> list = new ArrayList<>();
		NodeList nodebooks = root.getChildNodes();
		Node node = null;
		
		for (int i = 0; i < nodebooks.getLength(); i++) {
			node = nodebooks.item(i);
			
			if (node.getNodeType() != Node.TEXT_NODE) {
				getElement(node, book, author);
			} else {
				if (!node.getNodeValue().trim().isEmpty()) {
					book = setValueBookFields (node, book, author);
		   }}
		}	
	}

	private static Book setValueBookFields (Node node, Book book,  Author author) {
	   String nodeStr = node.getParentNode().getNodeName();
	   switch (nodeStr) {
	   case "title": 
		   book.setTitle(node.getTextContent());
		   break;
	   case "pages": 
		   book.setPages(Integer.parseInt(node.getTextContent()));
		   break;
	   case "author": 
		   
		   break;
	   default:
		   book = setValueAuthorFields (node, nodeStr, book);
		   break;
	 }
	  
	    return book;
	}
	
	private static Book setValueAuthorFields (Node node, String nodeStr, Book book) {
		switch (nodeStr) {
		   case "name": 
			   book.getAuthor().setName(node.getTextContent());
			   break;
		   case "surname": 
			   book.getAuthor().setSurname(node.getTextContent());
			   break;
		   case "age": 
			   book.getAuthor().setAge (Integer.parseInt(node.getTextContent()));
			   System.out.println(book);
			   break;
		   default:
			   break;
		 }	
	return book;
   }
		
}





/*
 
NodeList books = root.getChildNodes();
                      
for (int i = 0; i < books.getLength(); i++) {
    Node book = books.item(i);
    
    if (book.getNodeType() != Node.TEXT_NODE) {
    	 NodeList bookProps = book.getChildNodes();
    	   for(int j = 0; j < bookProps.getLength(); j++) {
                Node bookProp = bookProps.item(j);
                
                if (bookProp.getNodeType() != Node.TEXT_NODE) {
                	System.out.println(bookProp.getNodeName() + ":" + bookProp.getTextContent());	
                }
             
            	                        	  
               }
            
        }
    }
*/


