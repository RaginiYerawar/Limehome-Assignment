package com.qa.base;

import java.util.Locale.Category;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.microsoft.playwright.Page;
import com.qa.factory.PlaywrightFactory;
import com.qa.pages.CategoryPage;
import com.qa.pages.CheckoutPage;
import com.qa.pages.HomePage;
import com.qa.pages.ProductPage;

public class BaseTest {
	
	PlaywrightFactory pf;
	Properties prop;
	Page page;
	protected HomePage homePage;
	protected CategoryPage categoryPage;
	protected ProductPage productPage;
	protected CheckoutPage checkoutPage;
	protected JsonObject testdata;
	
	@BeforeClass
	public void readTestDataJSON() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		String filePath = System.getProperty("user.dir") + "/src/test/java/com/qa/testdata/TestData.json";
		Gson gson = new Gson();
		testdata = gson.fromJson(new FileReader(filePath), JsonObject.class);
	}
	
	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}
