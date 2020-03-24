package com.investsmart.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investsmart.server.asset.AssetModel;
import com.investsmart.server.asset.AssetRepository;
import com.investsmart.server.favcompany.FavCompanyModel;
import com.investsmart.server.favcompany.FavCompanyRepository;
import com.investsmart.server.user.UserModel;
import com.investsmart.server.user.UserRepository;

@Service
@Transactional
public class TheService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AssetRepository assetRepo;
	
	@Autowired
	private FavCompanyRepository favCompRepo;
	
	public void addUser(UserModel newUser) {
		System.out.println(newUser.toString());
		userRepo.save(newUser);
	}
	
	public UserModel getUser(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}
	
	// TO DO: Change Username or Password Implementation
	
	public List<AssetModel> getAssets(String email){
		return assetRepo.findByEmail(email);
	}
	
	public List<FavCompanyModel> getFavComp(String email){
		return favCompRepo.findByEmail(email);
	}
	
}
