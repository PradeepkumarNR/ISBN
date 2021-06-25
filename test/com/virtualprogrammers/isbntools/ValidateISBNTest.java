package com.virtualprogrammers.isbntools;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void should_allow_Valid_10_digit_ISBN() {
		
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("first value",result);
		result = validator.checkISBN("0140177396");
		assertTrue("second value ",result);
		
		
	}
	
	@Test
	
	void should_accept_10_Digit_isbnnumbers_ending_withX() {
		
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	@Test
	void should_allow_13_digit_num() {
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("9789352667789");
		assertTrue("first value of 13 digit",result);
	}
	
	@Test
	void should_allow_13_digit_num_secondTest() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9788186719480");
		assertTrue("secondValue of 13 digit",result);
	}
	
	@Test
	void should_notAllow_invalid_10_digit_ISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	void should_notAllow_invalid_13_digit_ISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9788186719481");
		assertFalse(result);
	}
	
	@Test
	void should_not_allow_9_digit_num() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, () ->{ validator.checkISBN("123456789");});
	}
	
	@Test
	void should_not_allow_11_digit_num() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, () ->{ validator.checkISBN("12345678912");});
	}
	
	

	@Test
	void should_not_allow_non_Numerics() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, () ->{ validator.checkISBN("helloWorld");});
	}
	
	

}
