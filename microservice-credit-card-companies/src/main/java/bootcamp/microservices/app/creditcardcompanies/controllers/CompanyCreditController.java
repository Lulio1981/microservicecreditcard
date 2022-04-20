package bootcamp.microservices.app.creditcardcompanies.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.microservices.app.creditcardcompanies.documents.CompanyCreditCard;
import bootcamp.microservices.app.creditcardcompanies.services.CompanyCreditCardService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompanyCreditController {

	@Autowired
	private CompanyCreditCardService companyCreditCardService;

	@PostMapping
	public Mono<CompanyCreditCard> createCompanyCreditCard(@Valid @RequestBody CompanyCreditCard companyCreditCard) {
		return companyCreditCardService.save(companyCreditCard);
	}

	@GetMapping("/all")
	public Flux<CompanyCreditCard> searchAll() {
		return companyCreditCardService.findAll();
	}

	@GetMapping("/id/{id}")
	public Mono<CompanyCreditCard> searchById(@PathVariable String id) {
		return companyCreditCardService.findById(id);
	}

	@PutMapping
	public Mono<CompanyCreditCard> updateCompanyCreditCard(@RequestBody CompanyCreditCard companyCreditCard) {
		return companyCreditCardService.update(companyCreditCard);
	}

	@DeleteMapping
	public Mono<CompanyCreditCard> deleteCcompanyCreditCard(@Valid @RequestBody CompanyCreditCard companyCreditCard) {
		return companyCreditCardService.deleteLogic(companyCreditCard);
	}

	@GetMapping("/idCompany/{idCompany}")
	public Mono<CompanyCreditCard> searchByIdCompany(@PathVariable String idCompany) {
		return companyCreditCardService.findByIdCompany(idCompany);
	}
}
