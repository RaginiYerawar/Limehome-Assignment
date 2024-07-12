package com.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CategoryPage {
	
	private Page page;

	public CategoryPage(Page page) {
		// TODO Auto-generated constructor stub
		this.page = page;
	}
	
	//Locators - ObjectRepo
	private String printedDress = "h5 a[title='Printed Dress']";
	
	//Actions
	public ProductPage clickOnPrintedDressProduct() {	
		Locator productLink = page.locator(printedDress);
		productLink.click(new Locator.ClickOptions().setForce(true));
		return new ProductPage(page);
	}

}
