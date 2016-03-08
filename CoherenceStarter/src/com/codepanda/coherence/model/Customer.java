package com.codepanda.coherence.model;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Customer implements PortableObject {
	
	private String surName;
	private String address;
	private String email;
	private String mobileTelephoneNumber;
	private int zipCode;
	private String name;
	
	
	
	/**
	 * @param surName
	 * @param address
	 * @param email
	 * @param mobileTelephoneNumber
	 * @param zipCode
	 * @param name
	 */
	public Customer(String surName, String address, String email, String mobileTelephoneNumber, int zipCode,
			String name) {
		
		super();
		this.surName = surName;
		this.address = address;
		this.email = email;
		this.mobileTelephoneNumber = mobileTelephoneNumber;
		this.zipCode = zipCode;
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileTelephoneNumber() {
		return mobileTelephoneNumber;
	}

	public void setMobileTelephoneNumber(String mobileTelephoneNumber) {
		this.mobileTelephoneNumber = mobileTelephoneNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void readExternal(PofReader pofReader) throws IOException {
		name = pofReader.readString(NAME);
		surName = pofReader.readString(SURNAME);
		address = pofReader.readString(ADDRESS);
		email = pofReader.readString(EMAIL);
		mobileTelephoneNumber = pofReader.readString(MOBILETELEPHONENUMBER);
		zipCode = pofReader.readInt(ZIPCODE);
	}

	@Override
	public void writeExternal(PofWriter pofWriter) throws IOException {
		pofWriter.writeString(NAME,name);
		pofWriter.writeString(SURNAME, surName);
		pofWriter.writeString(ADDRESS, address);
		pofWriter.writeString(EMAIL, email);
		pofWriter.writeString(MOBILETELEPHONENUMBER, mobileTelephoneNumber);
		pofWriter.writeInt(ZIPCODE, zipCode);

	}
	
	private final int NAME=0;
	private final int SURNAME=1;
	private final int ADDRESS=2;
	private final int EMAIL=3;
	private final int MOBILETELEPHONENUMBER=4;
	private final int ZIPCODE=5;
}
