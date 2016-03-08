package com.codepanda.coherence.test;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TryIt {

	public static void main(String[] args) {
		
		NamedCache cache=CacheFactory.getCache("employees");
		cache.put("name", "Hem Chandra");
		
		System.out.println("Name:"+cache.get("name"));
		System.out.println("Length:"+cache.size());
	}

}
