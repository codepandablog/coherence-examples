package com.codepanda.coherence.test;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

public class Contact implements PortableObject {

	/**
	 * * Approximate number of millis in a year ignoring things such as leap *
	 * years. Suitable for example use only.
	 */
	public static final long MILLIS_IN_YEAR = 1000L * 60L * 60L * 24L * 365L;
	private String firstName;
	private String lastName;
	private Address homeAddress;
	private Address workAddress;
	private Map telephoneNumbers;
	private java.sql.Date birthDate;

	public Contact(String firstName, String lastName, Address homeAddress, Address workAddress, Map telephoneNumbers,
			Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.telephoneNumbers = telephoneNumbers;
		this.birthDate = birthDate;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Map getTelephoneNumbers() {
		return telephoneNumbers;
	}

	public void setTelephoneNumbers(Map telephoneNumbers) {
		this.telephoneNumbers = telephoneNumbers;
	}

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
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
		StringBuffer sb = new StringBuffer(getFirstName()).append(" ").append(getLastName()).append("\nAddresses")
				.append("\nHome: ").append(getHomeAddress()).append("\nWork: ").append(getWorkAddress())
				.append("\nTelephone Numbers");
		for (Iterator iter = telephoneNumbers.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			sb.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
		}
		return sb.append("\nBirth Date: ").append(getBirthDate()).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this == null) {
			return false;
		}
		Contact that = (Contact) obj;
		return Base.equals(getFirstName(), that.getFirstName()) && Base.equals(getLastName(), that.getLastName())
				&& Base.equals(getTelephoneNumbers(), that.getTelephoneNumbers());
	}

	public int hashCode() {
		return HashHelper.hash(getFirstName(),
				HashHelper.hash(getLastName(), HashHelper.hash(getTelephoneNumbers(), 0)));
	}

	public int getAge() {
		return (int) ((System.currentTimeMillis() - birthDate.getTime()) / MILLIS_IN_YEAR);
	}

}
