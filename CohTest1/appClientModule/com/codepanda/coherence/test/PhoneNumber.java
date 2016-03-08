package com.codepanda.coherence.test;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.HashHelper;

public class PhoneNumber implements PortableObject {

	private short accessCode;
	private short countryCode;
	private short areaCode;
	private int localNumber;

	public PhoneNumber(short accessCode, short countryCode, short areaCode, int localNumber) {
		super();
		this.accessCode = accessCode;
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.localNumber = localNumber;
	}

	public PhoneNumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public short getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(short accessCode) {
		this.accessCode = accessCode;
	}

	public short getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(short countryCode) {
		this.countryCode = countryCode;
	}

	public short getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(short areaCode) {
		this.areaCode = areaCode;
	}

	public int getLocalNumber() {
		return localNumber;
	}

	public void setLocalNumber(int localNumber) {
		this.localNumber = localNumber;
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {

		setAccessCode(reader.readShort(0));
		setCountryCode(reader.readShort(1));
		setAreaCode(reader.readShort(2));
		setLocalNumber(reader.readInt(3));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {

		writer.writeShort(0, getAccessCode());
		writer.writeShort(1, getCountryCode());
		writer.writeShort(2, getAreaCode());
		writer.writeInt(3, getLocalNumber());

	}

	@Override
	public int hashCode() {
		return HashHelper.hash(getAreaCode(), HashHelper.hash(getLocalNumber(), 0));

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		PhoneNumber that = (PhoneNumber) obj;
		return getAccessCode() == that.getAccessCode() && getCountryCode() == that.getCountryCode()
				&& getAreaCode() == that.getAreaCode() && getLocalNumber() == that.getLocalNumber();
	}

	@Override
	public String toString() {
		return "+" + getAccessCode() + " " + getCountryCode() + " "
				 + getAreaCode()   + " " + getLocalNumber(); 
	}

}
