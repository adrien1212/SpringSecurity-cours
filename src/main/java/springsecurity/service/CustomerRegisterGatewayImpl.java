package springsecurity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springsecurity.domain.Customer;
import springsecurity.persitance.CustomerJpaEntity;
import springsecurity.persitance.CustomerJpaRepository;
import springsecurity.service.dto.RegisterCustomerRequestModel;


@Getter
@Setter
@AllArgsConstructor
@Service
public class CustomerRegisterGatewayImpl implements CustomerRegisterGateway {

	private final CustomerJpaRepository customerJpaRepository;

	@Override
	public void save(Customer user) {
		CustomerJpaEntity customerJpaEntity = new CustomerJpaEntity(user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole(), LocalDateTime.now(),
				LocalDateTime.now());

		customerJpaRepository.save(customerJpaEntity);
	}

	@Override
	public void update(RegisterCustomerRequestModel dto, Long id) {
		CustomerJpaEntity customerJpa = customerJpaRepository.findById(id).get();

		if (dto.getMatchedPassword() != null)
			customerJpa.setPassword(dto.getMatchedPassword());

		if (dto.getEmail() != null)
			customerJpa.setEmail(dto.getEmail());

		if (dto.getPhoneNumber() != null)
			customerJpa.setMobile(dto.getPhoneNumber());

		customerJpaRepository.save(customerJpa);
	}

	@Override
	public boolean existsByEmail(String email) {
		return !customerJpaRepository.findByEmail(email).isEmpty();
	}

	@Override
	public Customer findByEmail(String email) {
		return customerJpaRepository.findByEmail(email).map(jpa -> {
			return jpaToDomain(jpa);
		}).orElse(null);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return !customerJpaRepository.findByMobile(phoneNumber).isEmpty();
	}

	@Override
	public boolean existsById(Long id) {
		return !customerJpaRepository.findById(id).isEmpty();
	}

	@Override
	public boolean pwdMatchBdd(String pwd, Long id) {
		CustomerJpaEntity customerJpa = customerJpaRepository.findById(id).get();

		return pwd.equals(customerJpa.getPassword());
	}

	private Customer jpaToDomain(CustomerJpaEntity jpaEntity) {
		return Customer.builder().email(jpaEntity.getEmail()).firstName(jpaEntity.getFirstName())
				.lastName(jpaEntity.getLastName()).password(jpaEntity.getPassword()).phoneNumber(jpaEntity.getMobile())
				.role(jpaEntity.getRole()).build();
	}

}
