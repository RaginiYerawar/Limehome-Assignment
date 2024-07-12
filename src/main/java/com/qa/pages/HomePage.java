package com.qa.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;
	
	//Locators - ObjectRepo
	private String searchBox = "#search_query_top";
	private String searchIcon = "#searchbox button";
	private String searchPageHeader = "h1 span.lighter";
	private String searchPageHeaderCounter = "h1 span.heading-counter";
	private String productsCounter = "ul.product_list.grid.row > li";
	private String womenDresses = "ul.menu-content a[title = 'Women']";
	private String eveningDresses = "#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(2) > ul > li:nth-child(2) > a";
	
	
	//Constructor
	public HomePage(Page page) {
		this.page = page;
	}
	
	//Actions
	public String doSearch(String productName) {
		page.fill(searchBox, productName);
		page.click(searchIcon);
		return page.textContent(searchPageHeader).trim();
	}
	
	public int getCountOnSearchHeader() {
		String headingCounterText = page.textContent(searchPageHeaderCounter);
        return Integer.parseInt(headingCounterText.replaceAll("\\D+", ""));
	}
	
	public int getProductsCount() {
		return page.locator(productsCounter).count();
	}
	
	public CategoryPage navigateToCategoryPage() {
		page.hover(womenDresses);
		page.click(eveningDresses);
		return new CategoryPage(page);
	}
}
