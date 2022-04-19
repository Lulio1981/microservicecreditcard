package bootcamp.microservices.app.creditcardcompanies.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import bootcamp.microservices.app.creditcardcompanies.documents.Company;
import bootcamp.microservices.app.creditcardcompanies.documents.CompanyCreditCard;
import reactor.core.publisher.Mono;

public interface CompanyCreditCardRepository extends ReactiveMongoRepository<CompanyCreditCard, String> {

	public Mono<CompanyCreditCard> findByCompany(Company company);
}
