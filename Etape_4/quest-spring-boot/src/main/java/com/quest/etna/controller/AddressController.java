package com.quest.etna.controller;

import com.quest.etna.model.Address;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
	
	//TODO : 
	// User user ROLE_USER ==> acces à son addresse 
	// User user ROLE_ADMIn ==> acces à tte les adresses
	
	@GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Address> getAddresses() {
		// TODO 
		// check role user 
			// si ROLE_ADMIN return toutes les addresses
			// si non return que son address
		// intégrer try except pour return 500
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
