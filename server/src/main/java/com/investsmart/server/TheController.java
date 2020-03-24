package com.investsmart.server;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investsmart.server.asset.AssetModel;
import com.investsmart.server.favcompany.FavCompanyModel;
import com.investsmart.server.user.UserModel;

@CrossOrigin(origins = "*")
@RestController
public class TheController {
	
    @Autowired
    TheService service;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
    	return "Hello World!";
    }
    
    @RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public boolean addUser(@RequestBody UserModel newUser) {
    	try {
            service.addUser(newUser);
            return true;
    	}catch(Error e){
    		System.out.println(e.toString());
    	}
		return false;

    }
    
    @RequestMapping(value = "/get-user/{email}/{password}", method = RequestMethod.GET)
    public boolean getUser(@PathVariable String email, @PathVariable String password) {
    	UserModel user = service.getUser(email, password);
		return user != null;
    }
    
    @RequestMapping(value = "/get-assets/{email}", method = RequestMethod.GET)
    public List<AssetModel> getAssets(@PathVariable String email){
    	return service.getAssets(email);
    }

    @RequestMapping(value = "/get-fav-comp/{email}", method = RequestMethod.GET)
    public List<FavCompanyModel> getFavComp(@PathVariable String email){
    	return service.getFavComp(email);
    }
    
}
