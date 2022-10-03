package de.telran.new_project_test.repository;

import de.telran.new_project_test.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Boolean existsByNumber(String number);
    Optional<Phone> findByNumber(String number);
    List<Phone> findAllByPersonId(Integer personId);
}
