package edu.nbcc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.nbcc.model.Book;
import edu.nbcc.model.BookModel;
import edu.nbcc.model.ErrorModel;
import edu.nbcc.util.ValidationUtil;

/**
 * Servlet implementation class BookController
 */
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOK_VIEW = "/books.jsp";
	private static final String CREATE_VIEW = "/book.jsp";
	private static final String BOOK_SUMMARY = "/bookSummary.jsp";

	private RequestDispatcher view;

	public RequestDispatcher getView() {
		return view;
	}

	public void setView(HttpServletRequest request, String viewPath) {
		view = request.getRequestDispatcher(viewPath);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		String path = request.getPathInfo();
		if (path == null) {
			request.setAttribute("vm", book.getBooks());
			setView(request, BOOK_VIEW);
		} else {
			String[] paths = path.split("/");
			if (paths[1].equalsIgnoreCase("create") || ValidationUtil.isNumeric(paths[1])) {
				setView(request, CREATE_VIEW);
				int id = ValidationUtil.getInteger(paths[1]);
				if (id != 0) {
					Book bk = book.getBook(id);
					if (bk != null) {
						BookModel vm = new BookModel();
						vm.setBook(bk);
						request.setAttribute("vm", vm);
					} else {
						request.setAttribute("error", new ErrorModel("Book not found"));
					}
				} else {
					request.setAttribute("vm", new BookModel());
				}

			}
		}
		getView().forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Declare errors list
		List<String> errors = new ArrayList<String>();
		Book book = new Book();
		int id = 0;
		double price = 0;
		int term = 0;
		setView(request, BOOK_SUMMARY);

		try {
			if (request.getParameter("bookPrice") == null) {
				errors.add("Book price is null");
			}

			if (request.getParameter("bookName") == null) {
				errors.add("Book name is null");
			}

			if (request.getParameter("bookTerm") == null) {
				errors.add("Book term is null");
			}

			// Check for valid book id and assign int
			if (ValidationUtil.isNumeric(request.getParameter("hdnBookId"))) {
				id = ValidationUtil.getInteger(request.getParameter("hdnBookId"));
			} else {
				errors.add("Invalid Id");
			}

			// Assign book name, check for null later for validation
			String name = request.getParameter("bookName");
			if (name == null || name.equals("")) {
				errors.add("Name field cannot be null or blank");
			}

			// Check for valid book price and assign double
			if (ValidationUtil.isNumeric(request.getParameter("bookPrice"))) {
				price = ValidationUtil.getDouble(request.getParameter("bookPrice"));

				if (price == 0.0) {
					errors.add("Price cannot be 0");
				}
			} else {
				errors.add("Invalid Price");
			}

			// Check to ensure term was selected and assign to int
			if (request.getParameter("bookTerm") != null) {
				if (ValidationUtil.isNumeric(request.getParameter("bookTerm"))) {
					term = ValidationUtil.getInteger(request.getParameter("bookTerm"));
				} else {
					errors.add("Invalid term");
				}
			} else {
				errors.add("Please select a term");
			}

			if (errors.size() == 0) {
				book.setId(id);
				book.setName(name);
				book.setPrice(price);
				book.setTerm(term);

				String action = request.getParameter("action");

				switch (action) {
				case "Create":
					book.create();
					request.setAttribute("createdBook", book);
					break;
				case "Save":
					if (book.saveBook() > 0) {
						request.setAttribute("savedBook", book);
					} else {
						BookModel vm = new BookModel();
						vm.setBook(book);
						request.setAttribute("vm", vm);
						request.setAttribute("error", new ErrorModel("Book does not exist"));
						setView(request, CREATE_VIEW);
					}

					break;
				case "Delete":
					if (book.deleteBook() > 0) {
						request.setAttribute("deletedBook", book);
					} else {

					}
				}
			} else {
				BookModel vm = new BookModel();
				vm.setBook(book);
				setView(request, CREATE_VIEW);
				ErrorModel errorModel = new ErrorModel();
				errorModel.setErrors(errors);
				request.setAttribute("error", errorModel);
				request.setAttribute("vm", vm);
			}

		} catch (Exception e) {
			request.setAttribute("error", new ErrorModel("An error has occured"));
			setView(request, CREATE_VIEW);
		}
		getView().forward(request, response);
	}

}
