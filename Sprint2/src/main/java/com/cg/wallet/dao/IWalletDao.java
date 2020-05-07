package com.cg.wallet.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransaction;

public interface IWalletDao {
	
	
	public boolean addWalletAccount(WalletAccount account);
	public boolean editWalletAccount(WalletAccount account);
	public WalletAccount getWalletAccount(String walletAccountId);
	public boolean addWalletTransaction(WalletTransaction walletTxn);
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getWalletTransactions
	 * Description   : getting transactions of given user's walletUSerId 
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String walletUSerId
	 * 
	 **********************************************************************************/
	public List<WalletTransaction> getWalletTransactions(String walletUSerId);
	
	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getWalletTransactions
	 * Description   : getting number of transactions of given user's walletUSerId 
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String walletUSerId
	 * Parameter 2   : int txns
	 * 
	 **********************************************************************************/
	public List<WalletTransaction> getWalletTransactions(String walletUSerId, int txns);
	
	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getWalletTransactions
	 * Description   : getting transactions of given user's walletUSerId from fromDtate to toDate
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String walletUSerId
	 * Parameter 2   : LocalDate fromDate
	 * Parameter 3   : LocalDate toDate
	 * 
	 **********************************************************************************/
	public List<WalletTransaction> getWalletTransactions(String walletUSerId, LocalDate fromDt, LocalDate toDate);
	

}
