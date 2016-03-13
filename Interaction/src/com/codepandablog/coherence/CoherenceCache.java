package com.codepandablog.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCache {
	private NamedCache cache;
	public void putCache(){
		cache=CacheFactory.getCache("VirtualCache");
		String key="hello";
		cache.put(key, "Hello Cache!");
	}
	
	public void retrieveCache(){
		System.out.println((String)cache.get("hello"));
	}
	public CoherenceCache() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String arr[]){
		CoherenceCache cache=new CoherenceCache();
		cache.putCache();
		cache.retrieveCache();
	} 
}
