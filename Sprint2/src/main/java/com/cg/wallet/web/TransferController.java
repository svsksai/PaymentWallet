package com.cg.wallet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.dto.TransferForm;
import com.cg.wallet.dto.WalletMessage;
import com.cg.wallet.exceptions.AccountNotFoundException;
import com.cg.wallet.exceptions.WalletTxnException;
import com.cg.wallet.service.TransferFundService;
import com.cg.wallet.util.WalletConstants;

@RestController
public class TransferController {

	@Autowired
	private TransferFundService service;
	
	@CrossOrigin
	@PostMapping("/transfer")
	public WalletMessage transferAccount( @RequestBody TransferForm transferForm) throws AccountNotFoundException, WalletTxnException 
	{
		service.transferFund(transferForm);
		WalletMessage msg = new WalletMessage(WalletConstants.TRANSFER_SUCCESS);
		return msg;
		
	}
	
}
