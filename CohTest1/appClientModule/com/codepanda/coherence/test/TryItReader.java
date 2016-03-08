package com.codepanda.coherence.test;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TryItReader {

	public static void main(String[] args) {
		CacheFactory.ensureCluster();
		NamedCache cache=CacheFactory.getCache("employees");
		System.out.println("Name of cache:"+cache.getCacheName());
		System.out.println("Name of employee:"+cache.get("name"));
	}

}
