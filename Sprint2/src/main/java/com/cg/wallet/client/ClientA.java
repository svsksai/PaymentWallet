package com.cg.wallet.client;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cg.wallet.dto.TransferForm;
import com.cg.wallet.dto.TxnForm;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransaction;
import com.cg.wallet.exceptions.AccountNotFoundException;
import com.cg.wallet.exceptions.LoginException;
import com.cg.wallet.exceptions.WalletTXNNotFouException;
import com.cg.wallet.exceptions.WalletTxnException;
import com.cg.wallet.service.AddWalletService;
import com.cg.wallet.service.LoginService;
import com.cg.wallet.service.TransferFundService;
import com.cg.wallet.service.ViewWalletService;

public class ClientA {

	public static void main(String[] args) throws AccountNotFoundException, WalletTxnException, WalletTXNNotFouException, LoginException {
		
		  ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfiguration.class);
		  AddWalletService service = ctx.getBean(AddWalletService.class);
		 
		
		
		/*
		 * WalletAccount account = new WalletAccount(); TxnForm txnForm = new TxnForm();
		 * txnForm.setAmount(500); txnForm.setWalletAccountId("9949713956");
		 * account.setBalance(200); account.setPassword("svsk1234");
		 * account.setPhoneNo("9949713956"); account.setUserName("RamaRaju");
		 * service.createAccount(account); service.addAmountToWalletAccount(txnForm);
		 */
		 
		
		
		
		  WalletAccount act=new WalletAccount(); act.setPhoneNo("9949713956");
		  TransferFundService transferService = ctx.getBean(TransferFundService.class);
		  TransferForm transferForm = new TransferForm(); 
		  transferForm.setAmt(350);
		  transferForm.setFromaccountID("9949713956");
		  transferForm.setToAccountID("7013625372");
		  
		  transferService.transferFund(transferForm);
		  
		  
		  
		  LoginService loginService = ctx.getBean(LoginService.class);
		  System.out.println(loginService.doLogin("9949713956", "svsk1234"));
		 
	}

}
