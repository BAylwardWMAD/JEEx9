/**
 * 
 */
package edu.nbcc.model;

/**
 * @author brian
 *
 */
public class BookModel {
	private Book book = new Book();

	private final int[] terms = new int[] { 1, 2, 3, 4, 5, 6 };

	public BookModel() {

	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the terms
	 */
	public int[] getTerms() {
		return terms;
	}

}
