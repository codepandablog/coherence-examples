package com.codepanda.coherence.test;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.HashHelper;

public class Contact implements PortableObject {

	/**
	 * * Approximate number of millis in a year ignoring things such as leap *
	 * years. Suitable for example use only.
	 */
	public static final long MILLIS_IN_YEAR = 1000L * 60L * 60L * 24L * 365L;
	private String FirstName;
	private String LastName;
	private Address HomeAddress;
	private Address WorkAddress;
	private Map TelephoneNumbers;
	private java.sql.Date BirthDate;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String firstName, String lastName, Address homeAddress, Address workAddress, Map telephoneNumbers,
			Date birthDate) {
		super();
		FirstName = firstName;
		LastName = lastName;
		HomeAddress = homeAddress;
		WorkAddress = workAddress;
		TelephoneNumbers = telephoneNumbers;
		BirthDate = birthDate;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Address getHomeAddress() {
		return HomeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		HomeAddress = homeAddress;
	}

	public Address getWorkAddress() {
		return WorkAddress;
	}

	public void setWorkAddress(Address workAddress) {
		WorkAddress = workAddress;
	}

	public Map getTelephoneNumbers() {
		return TelephoneNumbers;
	}

	public void setTelephoneNumbers(Map telephoneNumbers) {
		TelephoneNumbers = telephoneNumbers;
	}

	public java.sql.Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		BirthDate = birthDate;
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {

		setFirstName(reader.readString(0));
		setLastName(reader.readString(1));
		setHomeAddress((Address) reader.readObject(2));
		setWorkAddress((Address) reader.readObject(3));
		setTelephoneNumbers(reader.readMap(4, null));
		setBirthDate(new Date(reader.readLong(5)));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getFirstName());
		writer.writeString(1, getLastName());
		writer.writeObject(2, getHomeAddress());
		writer.writeObject(3, getWorkAddress());
		writer.writeMap(4, getTelephoneNumbers());
		writer.writeLong(5, getBirthDate().getTime());
	}

	@Override
	public String toString() {
		 StringBuffer sb = new StringBuffer(getFirstName()).append(" ")                
				 .append(getLastName())                
				 .append("\nAddresses")                
				 .append("\nHome: ")
				 .append(getHomeAddress())                
				 .append("\nWork: ")
				 .append(getWorkAddress())                
				 .append("\nTelephone Numbers"); 
	        for (Iterator iter = TelephoneNumbers.entrySet().iterator();             
	        		iter.hasNext(); ){            
	        	Map.Entry entry = (Map.Entry) iter.next();            
	        	sb.append("\n").append(entry.getKey())
	        	.append(": ").append(entry.getValue());            
	        	}        
	        return sb.append("\nBirth Date: ").append(getBirthDate()).toString(); 
	        }


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
	}
