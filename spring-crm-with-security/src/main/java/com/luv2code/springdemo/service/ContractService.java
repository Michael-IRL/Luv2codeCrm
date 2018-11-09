package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Contract;

public interface ContractService
{
	public List<Contract> getContracts();

	public Contract getContract(int id);
}
