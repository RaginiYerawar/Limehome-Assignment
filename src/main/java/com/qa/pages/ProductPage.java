package com.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage {

	private Page page;
	
	public ProductPage(Page page) {
		// TODO Auto-generated constructor stub
		this.page = page;
	}
	
	//Locators - ObjectRepo
	private String pinkColor = "#color_24";
	private String addToCartButton = "span:has-text('Add to cart')";
	private String addToCartSuccessMessage = "h2:nth-child(2)";
	private String proceedToCheckoutLink = "span:has-text('Proceed to checkout')";
	private String inStockAvailability = "span:has-text('In stock')";
	
	//Actions
	public void clickOnDifferentColorOption() {
		page.click(pinkColor);
	}
	
	public boolean isAddToCartVisible() {
		return page.locator(addToCartButton).isVisible();
	}
	
	public String addToCartProduct() {
		//page.click(addToCartButton);
		Locator addToCart = page.locator(addToCartButton);
		addToCart.scrollIntoViewIfNeeded();
		addToCart.click(new Locator.ClickOptions().setForce(true));
		return page.textContent(addToCartSuccessMessage).trim();
	}
	
	public CheckoutPage clickOnProceedToCheckout() {
		Locator proceedToCheckout = page.locator(proceedToCheckoutLink);
		proceedToCheckout.scrollIntoViewIfNeeded();
		proceedToCheckout.click(new Locator.ClickOptions().setForce(true));
		
		return new CheckoutPage(page);
	}

}
