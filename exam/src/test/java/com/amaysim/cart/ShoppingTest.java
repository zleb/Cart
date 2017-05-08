package com.amaysim.cart;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class ShoppingTest
{
 	@BeforeTest
	public void setUp()
	{
		PricingRules pricingRules = new PricingRules();
		pricingRules = createPricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);
	}

	public static PricingRules createPricingRules()
    {
        PricingRules rules = new PricingRules();
        rules.add(Item.UNLIMITED1GB, Promo.THREEFORTWO);
        rules.add(Item.UNLIMITED5GB, Promo.IFMORETHANTHREE39_9);
        rules.add(Item.UNLIMITED2GB, Promo.FREE1GBDATAPACK);
        rules.addPromoCode("I<3AMAYSIM");

        return rules;
    }

	@Test
	public void calculateScenario1()
	{
		PricingRules pricingRules = new PricingRules();
		pricingRules = createPricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);

		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED5GB);

		ArrayList<Item> items = new ArrayList();
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED5GB);

		Assert.assertEquals(cart.getAllItems(), items);
		Assert.assertEquals(cart.getTotal(), 94.7);
	}

	@Test
	public void calculateScenario2()
	{
		PricingRules pricingRules = new PricingRules();
		pricingRules = createPricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);

		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED5GB);
		cart.add(Item.UNLIMITED5GB);
		cart.add(Item.UNLIMITED5GB);
		cart.add(Item.UNLIMITED5GB);

		ArrayList<Item> items = new ArrayList();
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED5GB);
		items.add(Item.UNLIMITED5GB);
		items.add(Item.UNLIMITED5GB);
		items.add(Item.UNLIMITED5GB);

		Assert.assertEquals(cart.getAllItems(), items);
		Assert.assertEquals(cart.getTotal(), 209.4);
	}

	@Test
	public void calculateScenario3()
	{
		PricingRules pricingRules = new PricingRules();
		pricingRules = createPricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);

		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.UNLIMITED2GB);
		cart.add(Item.UNLIMITED2GB);

		ArrayList<Item> items = new ArrayList();
		items.add(Item.UNLIMITED1GB);
		items.add(Item.UNLIMITED2GB);
		items.add(Item.UNLIMITED2GB);
		items.add(Item.DATAPACK1GB);
		items.add(Item.DATAPACK1GB);

		Assert.assertEquals(cart.getAllItems(), items);
		Assert.assertEquals(cart.getTotal(), 84.7);
	}

	@Test
	public void calculateScenario4()
	{
		PricingRules pricingRules = new PricingRules();
		pricingRules = createPricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);

		cart.add(Item.UNLIMITED1GB);
		cart.add(Item.DATAPACK1GB, "I<3AMAYSIM");

		ArrayList<Item> items = new ArrayList();
		items.add(Item.UNLIMITED1GB);
		items.add(Item.DATAPACK1GB);

		Assert.assertEquals(cart.getAllItems(), items);
		Assert.assertEquals(cart.getTotal(), 31.32);
	}
}