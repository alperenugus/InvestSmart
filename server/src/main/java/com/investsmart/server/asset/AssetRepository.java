package com.investsmart.server.asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.investsmart.server.user.UserModel;

@Repository
public interface AssetRepository extends CrudRepository<AssetModel, Integer>, JpaSpecificationExecutor<AssetModel> {
	
	public List<AssetModel> findByEmail(String email);

}
