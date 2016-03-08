package com.codepanda.coherence.test;

import com.codepanda.coherence.model.Customer;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CustomerTest1 {

	public static void main(String[] args) {

		Customer cust = new Customer("Chandra", "Bangalore", "codepandablog@gmail.com", 
				"5321231231", 34111, "Hem");
		NamedCache cache = CacheFactory.getCache("customers");
		cache.put(1, cust) ;
		System.out.println("Cache:"+cache.size());
		
		CacheFactory.shutdown();
	}

}
