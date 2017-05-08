package com.amaysim.cart;

import java.util.ArrayList;
import java.util.HashMap;

public class PricingRules
{
    HashMap<Item, Promo> promos = new HashMap<Item, Promo>();
    ArrayList<String> promoCodes = new ArrayList();

    public PricingRules()
    {}

    public void add(Item item, Promo promo)
    {
        promos.put(item, promo);
    }

    public HashMap<Item, Promo> getPromos()
    {
        return promos;
    }

    public void addPromoCode(String code)
    {
        promoCodes.add(code);
    }

    public ArrayList getPromoCodes()
    {
        return promoCodes;
    }

}