package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class ProductPageTest extends BaseTest{
	
	/**
	 * This test verifies Add to Cart button visible when product is in stock
	 */
	@Test
	public void verifyAddToCartButtonVisible() {
		categoryPage = homePage.navigateToCategoryPage();
		productPage = categoryPage.clickOnPrintedDressProduct();
		productPage.clickOnDifferentColorOption();
		
		Assert.assertTrue(productPage.isAddToCartVisible());
	}
	
	/**
	 * This test verifies product is successfully added to cart
	 */
	@Test
	public void verifySuccessMessageAfterAddToCart() {
		categoryPage = homePage.navigateToCategoryPage();
		productPage = categoryPage.clickOnPrintedDressProduct();
		productPage.clickOnDifferentColorOption();
		String actualSuccessMessage = productPage.addToCartProduct();
		Assert.assertEquals(actualSuccessMessage, testdata.get("ExpectedAddToCartSuccessMessage").getAsString());
	}

}
