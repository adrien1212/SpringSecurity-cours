package springsecurity.persitance;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import springsecurity.domain.Role;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerJpaEntity {
	@SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
	private int id;

	@NonNull
	@Column(name = "first_name")
	private String firstName;

	@NonNull
	@Column(name = "last_name")
	private String lastName;

	@NonNull
	@Column(name = "email", unique = true)
	private String email;

	@NonNull
	@Column(name = "password")
	private String password;

	@NonNull
	@Column(name = "mobile", unique = true)
	private String mobile;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "created_at", updatable = false)
	@NonNull
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@NonNull
	private LocalDateTime updatedAt;

}
