package com.virtualprogrammers.isbntools;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockManagementTests {

	ExternalISBNdataService testwebService;
	ExternalISBNdataService testdataservice;
	StockManager teststockmanager;

	@BeforeEach
	public void setup() {
		testwebService = mock(ExternalISBNdataService.class);
		testdataservice = mock(ExternalISBNdataService.class);
		teststockmanager = new StockManager();
		teststockmanager.setWebservice(testwebService);
		teststockmanager.setDataservice(testdataservice);
	}

	/*
	 * @Test void should_get_correct_locator_code() {
	 * 
	 * ExternalISBNdataService testservice = new ExternalISBNdataService() {
	 * 
	 * @Override public Book loockup(String isbn) { return new Book(isbn,
	 * "Vikram Sampath", "Echoes from a Forgotten Past"); } };
	 * 
	 * StockManager stockmanager = new StockManager();
	 * stockmanager.setService(testservice); String isbn = "0670090301"; String
	 * locatorcode = stockmanager.getlocatorCode(isbn); assertEquals("0301V5",
	 * locatorcode); }
	 */

	/*
	 * //Behavior change== look up is looking towards webservice to return the
	 * details of book if not //it will look towards database to return the book
	 * //External data service is now 2type 1.web and 2.database
	 */
	@Test
	public void should_get_the_correct_locator_from_web_When_database_isNull() {
		/*
		 * ExternalISBNdataService testwebservice = new ExternalISBNdataService() {
		 * 
		 * @Override public Book loockup(String isbn) { return new Book(isbn,
		 * "Vikram Sampath", "Echoes from a Forgotten Past"); } };
		 * 
		 * ExternalISBNdataService testdataservice = new ExternalISBNdataService() {
		 * 
		 * @Override public Book loockup(String isbn) { return null; } };
		 */

		// when we use fake and dummies, not interested in the text what we pass

		when(testwebService.loockup(anyString()))
				.thenReturn(new Book("0670090301", "Vikram Sampath", "Echoes from a Forgotten Past"));

		when(testdataservice.loockup(anyString())).thenReturn(null);

		String isbn = "0670090301";
		String locatorcode = teststockmanager.getlocatorCodeFromWebWhenDatabaseisNull(isbn);
		assertEquals("0301V5", locatorcode);

	}

	// we need to know when book is present in both web and Database, database is
	// used

	@Test
	public void should_use_database_when_data_is_present_in_database() {

		when(testdataservice.loockup("0670090301")).thenReturn(new Book("0670090301", "abc,", "cde"));

		String isbn = "0670090301";
		teststockmanager.getlocatorCodeFromWebWhenDatabaseisNull(isbn);
		verify(testdataservice, times(1)).loockup("0670090301");
		verify(testwebService, times(0)).loockup("0670090301");
		// same As Above-->verify(webservice, never()).loockup("0670090301");

	}

	@Test
	public void should_use_webservice_when_data_is_not_present_in_database() {
// Arrange
		when(testdataservice.loockup("0670090301")).thenReturn(null);
		when(testwebService.loockup("0670090301")).thenReturn(new Book("0670090301", "abc,", "cde"));

		String isbn = "0670090301";
		teststockmanager.getlocatorCodeFromWebWhenDatabaseisNull(isbn);
		verify(testdataservice, times(1)).loockup("0670090301");
		// same As Above--verify(databaseservice).loockup("0670090301");

		verify(testwebService, times(1)).loockup("0670090301");
		// same As Above--verify(webservice).loockup("0670090301");

	}

}
