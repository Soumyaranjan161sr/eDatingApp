package com.soumya.dating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.soumya.dating.controllers.UserAccountController;
import com.soumya.dating.entities.Interest;
import com.soumya.dating.entities.UserAccount;
import com.soumya.dating.respos.InterestsRepository;
import com.soumya.dating.respos.UserAccountRepository;

@SpringBootTest
class DatingAppApplicationTests {

	
	@Mock
	UserAccountRepository userAccountRepository;
	@Mock
	InterestsRepository interestsRepository;
	
	@InjectMocks
	UserAccountController controller;
	
	@Test
	void testRegisterUser() {
		UserAccount userAccount = new UserAccount();
		UserAccount savedUser = new UserAccount();
		savedUser.setId(123);
		when(userAccountRepository.save(userAccount)).thenReturn(savedUser);
		
		UserAccount outputUserAccount = controller.registerUser(userAccount);
		assertNotNull(outputUserAccount);
		assertNotNull(outputUserAccount.getId());
		assertEquals(123,outputUserAccount.getId());
		verify(userAccountRepository).save(userAccount);
	}
	
	@Test
	void testUpdateuser() {
		
			Interest interest = new Interest();
			interest.setUserAccountId(123);
			when(userAccountRepository.findById(123)).thenReturn(Optional.of(new UserAccount()));
			Interest savedInterest = new Interest();
			savedInterest.setId(123);
			when(interestsRepository.save(interest)).thenReturn(savedInterest);
			
			Interest outputInterest = controller.updateRequest(interest);
			assertNotNull(outputInterest);
			assertNotNull(outputInterest.getId());
			assertEquals(123,outputInterest.getId());
			verify(userAccountRepository).findById(123);
			verify(interestsRepository).save(interest);
		
	}
}
