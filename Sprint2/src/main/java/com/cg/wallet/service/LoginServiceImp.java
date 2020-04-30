package com.cg.wallet.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.exceptions.LoginException;

@Service
public class LoginServiceImp implements LoginService{

	@Autowired
	private IWalletDao dao;
	
	
	
	
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);
	
	@Override
	public WalletAccount doLogin(String userId, String password)throws LoginException {
		WalletAccount user = dao.getWalletAccount(userId);
		logger.debug("doing login process");
		
		if (user != null && user.getPassword().contentEquals(password)){
			logger.info("User Authenticated for " + userId);
			return user;
		}
		throw new LoginException("You are not authenticated and authorized, Please Login");
	}

	@Override
	public String encryptUser(WalletAccount user) {
		return encryptString(user.getPhoneNo())+"-" +encryptString(user.getUserName())+"-"
		      +encryptString(user.getRole());
	}
	
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]+3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]-3;
			sb.append((char)ch);
		}
		return sb.toString();
	}
}
