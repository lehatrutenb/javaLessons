package hse.kpo.repositories;

import hse.kpo.domains.Customer;
import hse.kpo.domains.catamarans.Catamaran;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Transactional
    @Modifying
    @Query("""
        DELETE
        FROM Customer c
        WHERE c.name = :name
    """)
    void deleteByName(@Param("name") String name);
}
