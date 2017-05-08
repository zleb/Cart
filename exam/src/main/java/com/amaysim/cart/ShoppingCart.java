package com.amaysim.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShoppingCart
{
    private PricingRules pricingRules;
    private double total = 0;
	private double discount = 0;
    private ArrayList<Item> items = new ArrayList();
    private ArrayList<Item> freeItems = new ArrayList();
    private HashMap<Item, Double> price = new HashMap<Item, Double>();
    
    public ShoppingCart(PricingRules rules)
    {
        addPrices();
        this.pricingRules = rules;
    }

    private void addPrices()
    {
        price.put(Item.UNLIMITED1GB, 24.9);
        price.put(Item.UNLIMITED2GB, 29.9);
        price.put(Item.UNLIMITED5GB, 44.9);
        price.put(Item.DATAPACK1GB, 9.9);
    }

    public void add(Item item)
    {
        items.add(item);
        computeTotal();
    }

    public void add(Item item, String promoCode)
    {
		checkPromoCode(promoCode);
        add(item);
    }

    public void checkPromoCode(String promoCode)
    {
        if (pricingRules.getPromoCodes().contains(promoCode))
        {
            if (promoCode.equals("I<3AMAYSIM"))
            {
                discount = .1;
            }
        }
    }

	public void printItems()
	{
		System.out.println("ITEMS: ");
		for (Item item : Item.values())
        {
			int freq = Collections.frequency(items, item);
			freq += Collections.frequency(freeItems, item);
			if (freq > 0)
			{
				System.out.print(freq + "X ");
				switch (item)
				{
					case UNLIMITED1GB:
						System.out.println("Unlimited 1 GB");
						break;
					case UNLIMITED2GB:
						System.out.println("Unlimited 2 GB");
						break;
					case UNLIMITED5GB:
						System.out.println("Unlimited 5 GB");
						break;
					case DATAPACK1GB:
						System.out.println("1 GB Data-pack");
						break;
					default:
						break;
				}
			}
		}
	}

	public void printTotal()
	{
		System.out.println("TOTAL: " + total);
	}

	public ArrayList<Item> getAllItems()
	{
		ArrayList<Item> allItems = new ArrayList();
		allItems.addAll(items);
		allItems.addAll(freeItems);

		return allItems;
	}

	public double getTotal()
	{
		return total;
	}

    private void addFreeItem(Item item)
    {
        freeItems.add(item);
    }

    private void computeTotal()
    {
        total = 0;
		freeItems.clear();
        for (Item item : Item.values())
        {
            Promo promo = pricingRules.getPromos().get(item);
            Double productPrice = price.get(item);
            int freq = 0;
			freq = Collections.frequency(items, item);
			if (promo == null)
			{
				total += productPrice * freq;
			}
			else
			{
				switch (promo)
				{
					case THREEFORTWO:
						total += productPrice * ((freq - freq % 3) / 3) * 2;
						total += productPrice * (freq % 3);
						break;
					case IFMORETHANTHREE39_9:
						if (freq > 3)
						{
							total += 39.9 * freq;
						}
						else
						{
							total += productPrice * freq;
						}
						break;
					case FREE1GBDATAPACK:
						total += productPrice * freq;
						for (int i = 0; i < freq; i++)
						{
							freeItems.add(Item.DATAPACK1GB);
						}
						break;
					default:
						break;
				}
			}
        }
		if (discount != 0)
			total *= 1 - discount;
		total = Math.round(total*100)/100.0d;
    }
}