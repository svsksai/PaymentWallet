package com.cg.wallet.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransaction;

@Repository
public class WalletDaoImpl implements IWalletDao{

	@PersistenceContext
	private EntityManager em;
	
	

	@Override
	public boolean addWalletAccount(WalletAccount account) {
		em.persist(account);
		return true;
	}

	@Override
	public boolean editWalletAccount(WalletAccount account) {
		em.merge(account);
		return true;
	}

	@Override
	public WalletAccount getWalletAccount(String walletAccountId) {
		
		return em.find(WalletAccount.class, walletAccountId);
	}

	@Override
	public boolean addWalletTransaction(WalletTransaction walletTxn) {
		em.persist(walletTxn);
		return true;
	}

	@Override
	public List<WalletTransaction> getWalletTransactions(String walletUserId) {
		String jpql = "from WalletTransaction wt inner join fetch wt.account wa  where wa.phoneNo=:walletUserid";
		TypedQuery<WalletTransaction> query = em.createQuery(jpql, WalletTransaction.class);
		query.setParameter("walletUserid", walletUserId);
		return query.getResultList();
	}

	@Override
	public List<WalletTransaction> getWalletTransactions(String walletUserId, int txns) {
		String jpql = "from WalletTransaction wt inner join fetch wt.account wa  where wa.phoneNo=:walletUserid order by wt.dateOfTransction desc";
		TypedQuery<WalletTransaction> query = em.createQuery(jpql, WalletTransaction.class);
		query.setFirstResult(1);
		query.setMaxResults(txns);
		query.setParameter("walletUserid", walletUserId);
		return query.getResultList();
	}
	@Override
	public List<WalletTransaction> getWalletTransactions(String walletUserId, LocalDate fromDt, LocalDate toDate) {
		String jpql = "from WalletTransaction wt inner join fetch wt.account wa  "
				+ "where wa.phoneNo=:walletUserid and wt.dateOfTranscation between :fromdt and :todt ";
		TypedQuery<WalletTransaction> query = em.createQuery(jpql, WalletTransaction.class);
		query.setParameter("walletUserid", walletUserId);
		query.setParameter("fromdt", fromDt);
		query.setParameter("todt", toDate);
		return query.getResultList();
	}

}






