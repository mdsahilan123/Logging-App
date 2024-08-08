package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;

@RestController
public class CustomerRestController {

	private CustomerRepo repo;
	@Autowired
	private PasswordEncoder pwdEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registration(@RequestBody Customer c) {
     
		String encodedPwd = pwdEncoder.encode(c.getPwd());
		c.setPwd(encodedPwd);
		repo.save(c);
		return new ResponseEntity<String>("User Registered", HttpStatus.CREATED);
	}
}
