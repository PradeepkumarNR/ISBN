package com.virtualprogrammers.isbntools;

public class StockManager {

	//private ExternalISBNdataService service;

	private ExternalISBNdataService webservice;

	private ExternalISBNdataService dataservice;

	// Behavior change== look up is looking towards webservice to return the details
	// of book if not
	// it will look towards database to return the book
	// External data service is now 2type 1.web and 2.database

	/*
	 * public void setService(ExternalISBNdataService service) { this.service =
	 * service; }
	 */

	public void setWebservice(ExternalISBNdataService webservice) {
		this.webservice = webservice;
	}

	public void setDataservice(ExternalISBNdataService dataservice) {
		this.dataservice = dataservice;
	}
	/*
	 * public String getlocatorCode(String isbn) {
	 * 
	 * Book book = service.loockup(isbn);
	 * 
	 * StringBuilder locator = new StringBuilder();
	 * locator.append(isbn.substring(isbn.length() - 4));
	 * locator.append(book.getAuthor().substring(0, 1));
	 * locator.append(book.getTitle().split(" ").length); return locator.toString();
	 * 
	 * }
	 */

	public String getlocatorCodeFromWebWhenDatabaseisNull(String isbn) {

		Book book = dataservice.loockup(isbn);
		if (book == null)
			book = webservice.loockup(isbn);
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length() - 4));
		locator.append(book.getAuthor().substring(0, 1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();

	}
}
