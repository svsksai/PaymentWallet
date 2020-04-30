
package com.cg.wallet.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.dto.ReportForm;
import com.cg.wallet.entity.WalletTransaction;
import com.cg.wallet.exceptions.WalletTXNNotFouException;
import com.cg.wallet.service.ViewWalletService;

@RestController
public class WalletViewcontroller {

	@Autowired
	private ViewWalletService walletService;
	
	@CrossOrigin
	@GetMapping("/getWalletTransactions/{walletid}")
	public List<WalletTransaction> getWalletTransactions(@PathVariable("walletid") String walletUSerId) throws WalletTXNNotFouException{
		return walletService.getWalletTransactions(walletUSerId);
	}
	
	
	@CrossOrigin
	@PostMapping("/getWalletTxnsForDateRange")
	public List<WalletTransaction> getWalletTransactions(@RequestBody ReportForm form) throws WalletTXNNotFouException{
			
		return walletService.getWalletTransactions(form.getWalletId(), form.getFromDt(), form.getToDt());
	}
	@CrossOrigin
	@GetMapping("/gettxnsrecipentfrom/{walletid}/{receipentid}")
	public List<WalletTransaction> getTxnsFromRecipent(@PathVariable("walletid") String walletUserId, @PathVariable("receipentid") String receipentId) throws WalletTXNNotFouException {
		return walletService.findAmountRecievedReceipent(walletUserId, receipentId);
	}
	
	@CrossOrigin
	@PostMapping("/gettxnsrecipentfromdaterange")
	public List<WalletTransaction> getTxnsFromRecipentForDateRange(@RequestBody ReportForm form) throws WalletTXNNotFouException {
		return walletService.findAmountREceivedToReceipent(form.getWalletId(), form.getReceipentId(), form.getFromDt(), form.getToDt());
	}
	
	@CrossOrigin
	@GetMapping("/gettxnsrecipentto/{walletid}/{receipentid}")
	public List<WalletTransaction> getTxnsToRecipent(@PathVariable("walletid") String walletUserId, @PathVariable("receipentid") String receipentId) throws WalletTXNNotFouException {
		return walletService.findAmountTransferedToReceipent(walletUserId, receipentId);
	}
	@CrossOrigin
	@PostMapping("/gettxnsrecipenttodaterange")
	public List<WalletTransaction> getTxnsToRecipentForDateRange(@RequestBody ReportForm form) throws WalletTXNNotFouException {
		return walletService.findAmountTransferedToReceipent(form.getWalletId(), form.getReceipentId(), form.getFromDt(), form.getToDt());
	}
	
	@CrossOrigin
	@GetMapping("/getWalletTransactions/six/{walletid}")
	public List<WalletTransaction> getWalletSixMonthsTransactions(@PathVariable("walletid") String walletUserId) throws WalletTXNNotFouException{
		return walletService.getSixMonthsTxn(walletUserId);
	}
	
}

