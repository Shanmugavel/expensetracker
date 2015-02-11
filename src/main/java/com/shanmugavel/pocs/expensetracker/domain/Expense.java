package com.shanmugavel.pocs.expensetracker.domain;

import java.util.Currency;
import java.util.Date;
import java.util.List;

public class Expense {

	private Date dt;
	private List<String> tags;
	private Double amount;
	private Currency currency;
	private String desc;
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Expense [dt=" + dt + ", tags=" + tags + ", amount=" + amount
				+ ", currency=" + currency + ", desc=" + desc + "]";
	}
	
	
}
