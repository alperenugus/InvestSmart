package com.investsmart.server.asset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assets")
public class AssetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "company")
	private String company;
	
	
	@Column(name = "owned_shares")
	private int owned_shares;
	

	public AssetModel() {}


	public AssetModel(String email, String company, int owned_shares) {
		this.email = email;
		this.company = company;
		this.owned_shares = owned_shares;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public int getOwned_shares() {
		return owned_shares;
	}


	public void setOwned_shares(int owned_shares) {
		this.owned_shares = owned_shares;
	}
	
}
