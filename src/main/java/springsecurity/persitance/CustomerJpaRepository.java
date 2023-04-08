package springsecurity.persitance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
	Optional<CustomerJpaEntity> findByEmail(String email);

	Optional<CustomerJpaEntity> findByMobile(String mobile);

	Optional<CustomerJpaEntity> findById(Long idCustomer);
}