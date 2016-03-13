package com.codepandablog.coherence;

import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;

import com.tangosol.io.pof.PofPrincipal;
import com.tangosol.net.Service;
import com.tangosol.net.security.IdentityAsserter;

public class PasswordIdentityAsserter implements IdentityAsserter {

	public PasswordIdentityAsserter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Subject assertIdentity(Object oToken, Service service)
			throws SecurityException {
		if (oToken instanceof Object[]) {
			String sPassword = System.getProperty("coherence.password",
					"secret-password");
			Set setPrincipalUser = new HashSet();
			Object[] asName = (Object[]) oToken;
			// first name must be password
			if (((String) asName[0]).equals(sPassword)) {
				// prints the user name to server shell to ensure we are
				// communicating with it and to ensure user is validated
				System.out.println("Password validated for user: " + asName[1]);
				for (int i = 1, len = asName.length; i < len; i++) {
					setPrincipalUser.add(new PofPrincipal((String) asName[i]));
				}
				return new Subject(true, setPrincipalUser, new HashSet(),
						new HashSet());
			}
		}
		throw new SecurityException("Access denied");
	}

}
