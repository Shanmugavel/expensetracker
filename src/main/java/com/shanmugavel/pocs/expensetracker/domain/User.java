/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@Document(collection="users")
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailAddress;
	private List<Phone> phones;
	private Status status;
	private List<Expense> expenses;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public void addPhone(Phone phone) {
		if (null == phones) {
			phones = new ArrayList<Phone>();
		}
		phones.add(phone);
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", emailAddress="
				+ emailAddress + ", phones=" + phones + ", status=" + status
				+ ", expenses=" + expenses + "]";
	}
	
}

