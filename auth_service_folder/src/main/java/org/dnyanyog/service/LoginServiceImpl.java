package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encryption.EncryptionUtil;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepository userRepo;
	
	@Autowired
	LoginResponse response;
	
	@Autowired
	EncryptionUtil encryption;

	public LoginResponse validateUser(LoginRequest loginRequest) throws Exception {

		List<Users> liUser = userRepo.findByUsername(loginRequest.getUsername());

		if (liUser.size() == 1) {
			
			Users userData = liUser.get(0);
			
			String getEncryptedPassword = userData.getPassword();	
			String encryptPasswordInRequest = encryption.encrypt(loginRequest.getPassword());
			
			if(encryptPasswordInRequest.equalsIgnoreCase(getEncryptedPassword)) {
				response.setStatus("Success");
				response.setMessage("Login successful");
			} else {
				response.setStatus("Fail");
				response.setMessage("Login failed");
			}
		}
		return response;
	}
}
