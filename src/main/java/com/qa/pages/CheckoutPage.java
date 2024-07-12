package com.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CheckoutPage {
	
	private Page page;

	public CheckoutPage(Page page) {
		// TODO Auto-generated constructor stub
		this.page = page;
	}
	
	//Locators - ObjectRepo
	private String checkoutButton = "a.button.btn.btn-default.standard-checkout.button-medium";
	private String processAddress = "button[name='processAddress']";
	private String checkboxTermsOfService = "#cgv";
	private String processCarrier = "button[name='processCarrier']";
	private String unitPriceLocator = "li.price";
	private String productQuantityLocator = "input.cart_quantity_input";
	private String totalProductsCostLocator = "#total_product";
	private String shippingCostLocator = "#total_shipping";
	private String totalPriceLocator = "#total_price";
	private String payByWireOption = "a.bankwire";
	private String payByCheckOption = "a.cheque";
	private String confirmOrderButton = "button.button.btn.btn-default.button-medium";
	private String orderConfirmSuccessMessage = "p.alert-success";
	private String username = "#email";
	private String password = "#passwd";
	private String loginButton = "#SubmitLogin";

	
	
	//Actions
	public void clickOnCheckoutButton(){
		Locator proceedToCheckout = page.locator(checkoutButton);
		proceedToCheckout.scrollIntoViewIfNeeded();
		proceedToCheckout.click(new Locator.ClickOptions().setForce(true));
	}
	
	public void clickOnCheckoutAfterProcessAddress(){
		Locator processAddressCheckout = page.locator(processAddress);
		processAddressCheckout.scrollIntoViewIfNeeded();
		processAddressCheckout.click(new Locator.ClickOptions().setForce(true));
	}
	
	public void clickOnCheckoutAfterProcessCarrier(){
		Locator processCarrierCheckout = page.locator(processCarrier);
		processCarrierCheckout.scrollIntoViewIfNeeded();
		processCarrierCheckout.click(new Locator.ClickOptions().setForce(true));
	}
	
	public boolean isTotalPriceSameAsProductAndShippingCost() {
		
		int unitProductPrice = Integer.parseInt(page.textContent(unitPriceLocator).replaceAll("\\D+", ""));
		int productQuantity =  Integer.parseInt(page.locator(productQuantityLocator).getAttribute("value"));
		int totalProductsCost = Integer.parseInt(page.textContent(totalProductsCostLocator).replaceAll("\\D+", ""));
		int shippingCost = Integer.parseInt(page.textContent(shippingCostLocator).replaceAll("\\D+", ""));
		int totalPrice = Integer.parseInt(page.textContent(totalPriceLocator).replaceAll("\\D+", ""));
		
		if((unitProductPrice*productQuantity)==totalProductsCost) {
			if((totalProductsCost+shippingCost)==totalPrice)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void performLogin(String user, String pass) {
		page.fill(username, user);
		page.fill(password, pass);
		page.click(loginButton);
	}
	
	public void checkTermsOfService() {
		page.check(checkboxTermsOfService);
	}

	public void clickOnCheckPayment() {
		Locator payByCheckOptionLocator = page.locator(payByCheckOption);
		payByCheckOptionLocator.scrollIntoViewIfNeeded();
		payByCheckOptionLocator.click(new Locator.ClickOptions().setForce(true));
	}
	
	public String clickOnConfirmOrder() {
		Locator confirmOrderButtonLocator = page.locator(confirmOrderButton);
		confirmOrderButtonLocator.scrollIntoViewIfNeeded();
		confirmOrderButtonLocator.click(new Locator.ClickOptions().setForce(true));
		String confirmOderMessage = page.textContent(orderConfirmSuccessMessage).trim();
		return confirmOderMessage;
	}
	
}
