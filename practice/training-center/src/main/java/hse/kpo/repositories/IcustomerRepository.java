package hse.kpo.repositories;

import hse.kpo.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IcustomerRepository extends JpaRepository<Customer, Integer> {
}