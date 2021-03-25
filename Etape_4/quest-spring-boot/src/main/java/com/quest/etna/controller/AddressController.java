package com.quest.etna.controller;

import com.quest.etna.model.Address;
import com.quest.etna.model.User;
import com.quest.etna.model.UserDetails;
import com.quest.etna.model.UserRole;
import com.quest.etna.repositories.AddressRepository;
import com.quest.etna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
	
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private UserRepository userRepository;
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if(null == context)
			return false;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(null == authentication)
			return false;
		
		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if(role.equals(auth.getAuthority()))
				return true;
		}
		
		return false;
	}
		
	@GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getAddresses() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// TODO
		try {
			if(hasRole("ROLE_USER")) {
				User u = userRepository.findByUsername(auth.getPrincipal().toString());
				
		        Address userAddress = u.getAddress();

		        if (null != userAddress) {
		            return ResponseEntity.ok(userAddress);
		        }
			}
			
			if(hasRole("ROLE_ADMIN")) {
				// return toute les addresse
			}
		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
		return null;
	}
	
	@GetMapping(value = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Address getAddress() {
		// TODO 
		// check si id exist 
			// si oui pass
			// si non return 404
		// check role user 
			// si ROLE_ADMIN return l'address
			// si non check si address appartient au user
				// si oui return address
				// si non return 403
		// intégrer try except pour return 500
		return null;
	}
	
	@PostMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> postAddress(@RequestBody Address address) {
		// TODO 
		// check si body complet
			// si oui return 200
			// si non return 400
		// intégrer try except pour return 500
		return null;
	}
	
	@PutMapping(value = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> putAddress(@RequestBody Address address) {
		// TODO
		// check check si id exist 
			// si non return 404
		// check si le/s champs sont ok 
			// si non return 400
			// si oui return 200
		// intégrer try except pour return 500
		return null;
	}
	
	@DeleteMapping(value = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteAddress(@RequestBody Address address) {
		// TODO 
		// check si id exist 
			// si oui pass
			// si non return 404
		// check role user 
			// si ROLE_ADMIN delete l'address
			// si non check si address appartient au user
				// si oui delete address
				// si non return 403
		// intégrer try except pour return 500
		return null;
	}
}
