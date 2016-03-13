package com.codepandablog.coherence;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.extractor.IdentityExtractor;
import com.tangosol.util.filter.LikeFilter;

public class DatabaseCache {

	NamedCache cache;

	public void createCache() {
		cache = CacheFactory.getCache("DBBackedCache");
	}

	public void addEntry() {
		cache.put(new String("catalog3"), new String("Tuning Grid Management"));
		cache.put(new String("catalog4"), new String("Tuning Coherence"));
		cache.put(new String("catalog5"), new String("Tuning Database"));
	}

	public void retrieveEntry() {
		System.out.println((String) cache.get("catalog3"));
	}

	public void eraseEntry() {
		cache.remove(new String("catalog3"));
	}

	public void queryCache() {
		Filter filter = new LikeFilter(IdentityExtractor.INSTANCE, "Tuning%",
				'\\', true);
		HashSet hashSet = new HashSet();
		hashSet.add(new String("catalog3"));
		hashSet.add(new String("catalog4"));
		hashSet.add(new String("catalog5"));
		Map map = cache.getAll(hashSet);
		Set results = cache.entrySet(filter);
		for (Iterator i = results.iterator(); i.hasNext();) {
			Map.Entry e = (Map.Entry) i.next();
			System.out.println("Catalog ID: " + e.getKey() + ", Title: "
					+ e.getValue());
		}
	}

	public DatabaseCache() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		DatabaseCache databaseCache = new DatabaseCache();
		databaseCache.createCache();
		databaseCache.addEntry();
		databaseCache.queryCache();
	}

}
