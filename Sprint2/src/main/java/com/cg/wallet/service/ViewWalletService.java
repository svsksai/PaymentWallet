package com.cg.wallet.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.wallet.entity.WalletTransaction;
import com.cg.wallet.exceptions.WalletTXNNotFouException;

public interface ViewWalletService {

	public List<WalletTransaction> getWalletTransactions(String walletUSerId)throws WalletTXNNotFouException ;
	public List<WalletTransaction> getWalletTransactions(String walletUSerId, LocalDate fromDt, LocalDate toDate)throws WalletTXNNotFouException ;
	public List<WalletTransaction> findAmountTransferedToReceipent(String walletUSerId, String recipentID)throws WalletTXNNotFouException ;
	public List<WalletTransaction> findAmountTransferedToReceipent(String walletUSerId, String recipentID, LocalDate fromDt, LocalDate toDate)throws WalletTXNNotFouException ;
	public List<WalletTransaction> findAmountRecievedReceipent(String walletUSerId, String recipentID)throws WalletTXNNotFouException ;
	public List<WalletTransaction> findAmountREceivedToReceipent(String walletUSerId, String recipentID, LocalDate fromDt, LocalDate toDate)throws WalletTXNNotFouException ;
    public List<WalletTransaction> getSixMonthsTxn(String walletUserId)throws WalletTXNNotFouException ;
}
