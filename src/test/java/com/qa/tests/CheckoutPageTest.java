package com.qa.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class CheckoutPageTest extends BaseTest{

	/**
	 * This test verifies order is placed successfully
	 */
	@Test
	public void verifyOrderConfirmMessage(){
		categoryPage = homePage.navigateToCategoryPage();
		productPage = categoryPage.clickOnPrintedDressProduct();
		productPage.clickOnDifferentColorOption();
		productPage.addToCartProduct();
		checkoutPage = productPage.clickOnProceedToCheckout();
		checkoutPage.clickOnCheckoutButton();
		checkoutPage.performLogin(testdata.get("username").getAsString(), testdata.get("password").getAsString());
		checkoutPage.clickOnCheckoutAfterProcessAddress();
		checkoutPage.checkTermsOfService();
		checkoutPage.clickOnCheckoutAfterProcessCarrier();
		checkoutPage.clickOnCheckPayment();
		String actualConfirmMessage = checkoutPage.clickOnConfirmOrder();
		Assert.assertEquals(actualConfirmMessage, testdata.get("expectedOrderConfirmMessage").getAsString());
		
	}
	
	/**
	 * This test verifies cart value is sum of total products value and shipping charges.
	 */
	@Test
	public void verifyTotalCartValue(){
		categoryPage = homePage.navigateToCategoryPage();
		productPage = categoryPage.clickOnPrintedDressProduct();
		productPage.clickOnDifferentColorOption();
		productPage.addToCartProduct();
		checkoutPage = productPage.clickOnProceedToCheckout();
		assertTrue(checkoutPage.isTotalPriceSameAsProductAndShippingCost());
	}
	
}
