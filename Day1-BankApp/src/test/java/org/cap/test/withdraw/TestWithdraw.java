package org.cap.test.withdraw;

import junit.framework.Assert;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestWithdraw {
	
	@Mock
	AccountDao accountDao;
	AcccountService service;
	
	@Before
	public void setUp(){

	MockitoAnnotations.initMocks(this);
	service=new AccountServiceImpl(accountDao);
	}
	
	@Test
	public void test_Withdraw() throws InsufficientBalanceException{
		
		int accountNo=123;
		double amount=1000;
		Account account = new Account();
		account.setAccountNo(accountNo);
		account.setAmount(amount);
		Customer customer=new Customer();
		customer.setCustAddress(new Address());
		customer.setCustName("St");
		account.setCustomer(customer);
		
		Mockito.when(accountDao.findAccountById(accountNo)).thenReturn(account);
		
		Account withdraw=service.withdraw(accountNo, 500);
		
		Mockito.verify(accountDao).findAccountById(accountNo);
		Assert.assertEquals(500, withdraw.getAmount(),0.0);
		
		
		
		
		
	}
}