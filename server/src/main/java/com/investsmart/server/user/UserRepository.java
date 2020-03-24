package com.investsmart.server.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel>{

	public UserModel findByEmailAndPassword(String email, String password);
	
}
