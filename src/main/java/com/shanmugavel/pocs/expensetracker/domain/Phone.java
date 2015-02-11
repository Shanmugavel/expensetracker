package com.shanmugavel.pocs.expensetracker.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@Document
public class Phone {

	private PhoneType type;
	private String number;
	private boolean defaultPhone;
	
	public PhoneType getType() {
		return type;
	}
	public void setType(PhoneType type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isDefaultPhone() {
		return defaultPhone;
	}
	public void setDefaultPhone(boolean defaultPhone) {
		this.defaultPhone = defaultPhone;
	}
	@Override
	public String toString() {
		return "Phone [type=" + type + ", number=" + number + ", defaultPhone="
				+ defaultPhone + "]";
	}
	
}
