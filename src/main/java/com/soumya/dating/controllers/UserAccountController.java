package com.soumya.dating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.dating.entities.Interest;
import com.soumya.dating.entities.UserAccount;
import com.soumya.dating.respos.InterestsRepository;
import com.soumya.dating.respos.UserAccountRepository;

@RestController
@RequestMapping("/api")
public class UserAccountController {
	
	@Autowired
	private UserAccountRepository userRepo;
	@Autowired
	private InterestsRepository interestRepo;
	
		
		@PostMapping("/users/register-user")
		public UserAccount registerUser(@RequestBody UserAccount userAccount) {
			return userRepo.save(userAccount);
		}
		
		@PostMapping("/interests/update")
		public Interest updateRequest(@RequestBody Interest interest) {
			UserAccount userAccount = userRepo.findById(interest.getUserAccountId()).get();
			interest.setUserAccount(userAccount);
			return interestRepo.save(interest);
		}
		
		@GetMapping("/users/get/all")
		public List<UserAccount> getUsers(){
			return userRepo.findAll();
		}
		
		@DeleteMapping("/users/delete/{interestId}")
		public void deleteInterest(@PathVariable("interestId") int id) {
			interestRepo.deleteById(id);
		}
	
		@GetMapping("/users/matches/{id}")
		public List<UserAccount> findMatches(@PathVariable("id") int id){	
			UserAccount userAccount = userRepo.findById(id).get();
			return userRepo.findMatches(userAccount.getAge(), userAccount.getCity(),userAccount.getCountry(), userAccount.getId());
			
		}
}
