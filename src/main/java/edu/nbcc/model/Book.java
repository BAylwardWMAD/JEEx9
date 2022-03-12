/**
 * 
 */
package edu.nbcc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brian
 *
 */
public class Book {
	private int id;
	private String name;
	private double price;
	private int term;

	/**
	 * 
	 */
	public Book() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param term
	 */
	public Book(int id, String name, double price, int term) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.term = term;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}

	private List<Book> mockData = new ArrayList<Book>();

	private void buildMockData() {
		mockData.add(new Book(1, "Book 1", 19.99, 1));
		mockData.add(new Book(2, "Book 2", 11.97, 2));
		mockData.add(new Book(3, "Book 3", 12.96, 3));
		mockData.add(new Book(4, "Book 4", 13.95, 4));
		mockData.add(new Book(5, "Book 5", 14.94, 1));
		mockData.add(new Book(6, "Book 6", 15.93, 2));
		mockData.add(new Book(7, "Book 7", 16.92, 3));
	}

	/**
	 * 
	 * @return
	 */
	public List<Book> getBooks() {
		buildMockData();
		return this.mockData;
	}

	public Book getBook(int id) {
		List<Book> book = getBooks().stream().filter(b -> b.getId() == id).collect(Collectors.toList());
		if (book.size() > 0) {
			return book.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Book create() {
		this.id = this.mockData.size() + 1;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public int saveBook() {
		if(getBook(this.id)!= null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int deleteBook() {
		if (getBook(this.id) != null) {
			return 1;
		}
		return 0;
	}

}
