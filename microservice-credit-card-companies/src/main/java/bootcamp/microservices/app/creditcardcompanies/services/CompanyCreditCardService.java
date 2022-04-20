package bootcamp.microservices.app.creditcardcompanies.services;

import bootcamp.microservices.app.creditcardcompanies.documents.CompanyCreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyCreditCardService {

	public Flux<CompanyCreditCard> findAll();

	public Mono<CompanyCreditCard> findById(String id);

	public Mono<CompanyCreditCard> save(CompanyCreditCard companyCreditCard);

	public Mono<CompanyCreditCard> update(CompanyCreditCard companyCreditCard);

	public Mono<Void> deleteNonLogic(CompanyCreditCard companyCreditCard);

	public Mono<CompanyCreditCard> deleteLogic(CompanyCreditCard companyCreditCard);

	public Mono<CompanyCreditCard> findByIdCompany(String idCompany);

}
