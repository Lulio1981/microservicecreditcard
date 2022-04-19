package bootcamp.microservices.app.creditcardcompanies.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.microservices.app.creditcardcompanies.documents.CompanyCreditCard;
import bootcamp.microservices.app.creditcardcompanies.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.creditcardcompanies.repository.CompanyCreditCardRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyCreditCardServiceImpl implements CompanyCreditCardService {

	private static final Logger log = LoggerFactory.getLogger(CompanyCreditCardServiceImpl.class);

	@Autowired
	private CompanyCreditCardRepository companyCreditCardRepository;

	@Override
	public Flux<CompanyCreditCard> findAll() {
		return companyCreditCardRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Clients not exist")));
	}

	@Override
	public Mono<CompanyCreditCard> findById(String id) {
		return companyCreditCardRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCreditCard not found")));
	}

	@Override
	public Mono<CompanyCreditCard> update(CompanyCreditCard companyCredit) {
		return companyCreditCardRepository.findById(companyCredit.getId()).flatMap(c -> {
			companyCredit.setModifyUser(companyCredit.getModifyUser());
			companyCredit.setModifyDate(new Date());
			return companyCreditCardRepository.save(companyCredit);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCreditCard not found")));
	}

	@Override
	public Mono<CompanyCreditCard> save(CompanyCreditCard companyCredit) {
		return companyCreditCardRepository.save(companyCredit);
	}

	@Override
	public Mono<Void> deleteNonLogic(CompanyCreditCard companyCredit) {
		return companyCreditCardRepository.findById(companyCredit.getId()).flatMap(c -> {
			return companyCreditCardRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCreditCard not found")));
	}

	@Override
	public Mono<CompanyCreditCard> deleteLogic(CompanyCreditCard companyCredit) {
		return companyCreditCardRepository.findById(companyCredit.getId()).flatMap(c -> {
			c.setModifyUser(companyCredit.getModifyUser());
			c.setModifyDate(new Date());
			return companyCreditCardRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCreditCard not found")));
	}

}
