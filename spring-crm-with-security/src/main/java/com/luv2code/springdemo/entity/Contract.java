package com.luv2code.springdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contract")
public class Contract
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="contract_type")
	private String contractType;
	
	@Column(name="contract_description")
	private String description;
	
	@OneToMany(mappedBy="contract")	
	private List<Customer> customers;
	
	public Contract()
	{
		
	}

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getContractType()
	{
		return contractType;
	}
	public void setContractType(String contractType)
	{
		this.contractType = contractType;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}	

	public List<Customer> getCustomers()
	{
		return customers;
	}

	public void setCustomers(List<Customer> customers)
	{
		this.customers = customers;
	}
	
	public void addCustomer(Customer customer)
	{
		if(customers == null)
		{
			customers = new ArrayList<>();
		}
		
		customers.add(customer);
		customer.setContract(this);
	}

	@Override
	public String toString()
	{
		return "Contract [id=" + id + ", contractType=" + contractType + ", description=" + description + "]";
	}	
	
}
