package com.codepandablog.coherence;

import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class PasswordExample {

	public PasswordExample() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		getCache();

	}

	public static void getCache() {
		System.out.println("**********password example starts*******");
		Subject subject = SecurityExampleHelper.login("BuckarooBanzai");
		try {
			NamedCache cache = (NamedCache) Subject.doAs(subject,
					new PrivilegedExceptionAction() {
						public Object run() throws Exception {
							NamedCache cache;
							cache = CacheFactory
									.getCache(SecurityExampleHelper.SECURITY_CACHE_NAME);
							System.out
									.println("------password example succeeded------");
							return cache;
						}
					});
		} catch (Exception e) {
			System.out.println("Unable to connect to proxy");
			e.printStackTrace();
		}
		
		System.out.println("***************Password example completed***************");
	}

}
