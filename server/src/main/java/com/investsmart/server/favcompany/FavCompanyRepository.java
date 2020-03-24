package com.investsmart.server.favcompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavCompanyRepository extends CrudRepository<FavCompanyModel, Integer>, JpaSpecificationExecutor<FavCompanyModel> {

	public List<FavCompanyModel> findByEmail(String email);
	
}
