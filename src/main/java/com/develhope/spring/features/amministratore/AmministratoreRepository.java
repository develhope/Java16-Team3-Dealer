package com.develhope.spring.features.amministratore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends JpaRepository<Amministratore,Long> {
}
