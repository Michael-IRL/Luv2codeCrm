package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.ContractDAO;
import com.luv2code.springdemo.entity.Contract;

@Service
public class ContractServiceImpl implements ContractService
{
	@Autowired
	private ContractDAO contractDao;
	
	@Override
	@Transactional
	public List<Contract> getContracts()
	{
		return contractDao.getContracts();
	}

	@Override
	@Transactional
	public Contract getContract(int id)
	{
		
		return contractDao.getContract(id);
	}

}
