package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Contract;

@Repository
public class ContractDAOImpl implements ContractDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Contract> getContracts()
	{
		Session session = sessionFactory.getCurrentSession();
		
		List<Contract> contracts = session.createQuery("from Contract",Contract.class).getResultList();
		
		return contracts;
	}

	@Override
	public Contract getContract(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		
		Contract contract = session.get(Contract.class, id);
		
		return contract;
	}

	@Override
	public Contract getContractWithCustomers(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		
		Query<Contract> query = session.createQuery("select c from Contract c join fetch c.customers where c.id = :id",Contract.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

}
