package pl.dahdev.managementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dahdev.managementapp.model.Activation;

import java.util.Optional;

public interface ActivationRepository extends JpaRepository<Activation, Integer> {

    Optional<Activation> findById(long id);

    Optional<Activation> findByActivationCode(String activationCode);
}
