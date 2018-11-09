package com.luv2code.springdemo.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Contract;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.ContractService;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		List<Contract> theContracts = contractService.getContracts();
		
		LinkedHashMap<Integer,String> contractOptions = new LinkedHashMap<>();
		
		for(Contract tempContract: theContracts)
		{
			contractOptions.put(tempContract.getId(), tempContract.getContractType());
		}
		
		theModel.addAttribute("customer", theCustomer);
		theModel.addAttribute("contracts",contractOptions);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer, @RequestParam("contractId")int id) {
		
		System.out.println(">>> the pasted in customer "+theCustomer);
		System.out.println(">>> the pasted in contractId "+id);
		
		Contract contract = contractService.getContract(id);
		
		System.out.println(">>> fetched contract "+ contract);
		System.out.println(">>> Add the contract to the customer");
		
		theCustomer.setContract(contract);
		
		System.out.println(">>> the customer with contract added "+theCustomer);
		System.out.println(">>> Add the customer to the contract");
		
		contract.addCustomer(theCustomer);
		
		
		
		//customerService.saveCustomer(theCustomer);	
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}










