package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.qa.base.BaseTest;
import com.qa.factory.PlaywrightFactory;
import com.qa.pages.HomePage;

public class HomePageTest extends BaseTest{
	
	/**
	 * DataProvider for search input
	 * Includes different search scenarios such as combination of capital and small letters, 1 word, more than 1 word search
	 * @return
	 */
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
				{ "Summer" }, 
				{ "shirt" }, 
				{ "summer dress" }
		};
	}
	
	/**
	 * This test verifies Page Heading after search 
	 * @param productName
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "getSearchData")
	public void verifySearchHeaderAfterSearch(String productName) throws InterruptedException {
		Thread.sleep(2000);
		String actualSearchHeader = homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, '"'+productName+'"');
	}
	
	/**
	 * This test verifies counter on search header and actual number of products displayed
	 * @param productName
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "getSearchData")
	public void verifyProductsCountAfterSearch(String productName) throws InterruptedException {
		Thread.sleep(2000);
		homePage.doSearch(productName);
		int expectedProductCount = homePage.getCountOnSearchHeader();
		int actualProductCount = homePage.getProductsCount();
		Assert.assertEquals(actualProductCount, expectedProductCount);
	}
	
	
	
}
