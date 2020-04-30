package com.cg.wallet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.entity.WalletTransaction;
import com.cg.wallet.exceptions.WalletTXNNotFouException;
import com.cg.wallet.util.WalletConstants;

@Transactional
@Service
public class ViewWalletServiceImpl implements ViewWalletService {

	@Autowired
	private IWalletDao dao;

	//@Value("${txnlimit}")
	private int txnLimit= WalletConstants.TXN_LIMIT;
	
	public ViewWalletServiceImpl() {
		
	}
	
	@Override
	public List<WalletTransaction> getWalletTransactions(String walletUSerId) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().sorted((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation())).limit(txnLimit).collect(Collectors.toList());
		return txnList;
	}


	@Override
	public List<WalletTransaction> getWalletTransactions(String walletUSerId, LocalDate fromDt, LocalDate toDate) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList.sort((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation()));
		return txnList;
	}


	@Override
	public List<WalletTransaction> findAmountTransferedToReceipent(String walletUSerId, String recipentID) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().filter(txn->txn.getDescription().equalsIgnoreCase(WalletConstants.TRANSFERED_TO+recipentID))
		    .sorted((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation())).limit(txnLimit).collect(Collectors.toList());
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		return txnList;
	}


	@Override
	public List<WalletTransaction> findAmountTransferedToReceipent(String walletUSerId, String recipentID,
			LocalDate fromDt, LocalDate toDate) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().filter(txn->txn.getDescription().equalsIgnoreCase(WalletConstants.TRANSFERED_TO+recipentID))
			    .sorted((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation())).limit(txnLimit).collect(Collectors.toList());
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		return txnList;
	}


	@Override
	public List<WalletTransaction> findAmountRecievedReceipent(String walletUSerId, String recipentID) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().filter(txn->txn.getDescription().equalsIgnoreCase(WalletConstants.TRANSFERED_FROM+recipentID))
		    .sorted((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation())).limit(txnLimit).collect(Collectors.toList());
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		return txnList;
	}


	@Override
	public List<WalletTransaction> findAmountREceivedToReceipent(String walletUSerId, String recipentID,
			LocalDate fromDt, LocalDate toDate) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUSerId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().filter(txn->txn.getDescription().equalsIgnoreCase(WalletConstants.TRANSFERED_FROM+recipentID))
			    .sorted((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation())).limit(txnLimit).collect(Collectors.toList());
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		return txnList;
	}


	@Override
	public List<WalletTransaction> getSixMonthsTxn(String walletUserId) throws WalletTXNNotFouException {
		List<WalletTransaction> txnList =  dao.getWalletTransactions(walletUserId, LocalDate.now().minusMonths(WalletConstants.SIX_MONTHS), LocalDate.now());
		if(txnList.isEmpty())
			throw new WalletTXNNotFouException(WalletConstants.NO_TXN_AVAILABLE);
		txnList.sort((t1, t2)->t2.getDateOfTranscation().compareTo(t1.getDateOfTranscation()));
		return txnList;
	}
	

}
