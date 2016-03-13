package com.codepandablog.coherence;

import java.security.Principal;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.Subject;

import com.tangosol.net.Service;
import com.tangosol.net.security.IdentityTransformer;

public class PasswordIdentityTransformer implements IdentityTransformer {

	public PasswordIdentityTransformer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object transformIdentity(Subject subject, Service service)
			throws SecurityException {
		if (subject == null) {
			throw new SecurityException("Incomplete Subject");
		}
		Set setPrincipals = subject.getPrincipals();
		if (setPrincipals.isEmpty()) {
			throw new SecurityException("Incomplete Subject");
		}
		String[] asPrincipalName = new String[setPrincipals.size() + 1];
		int i = 0;
		asPrincipalName[i++] = System.getProperty("coherence.password",
				"secret-password");
		for (Iterator iter = setPrincipals.iterator(); iter.hasNext();) {
			asPrincipalName[i++] = ((Principal) iter.next()).getName();
		}
		return asPrincipalName;
	}

}
