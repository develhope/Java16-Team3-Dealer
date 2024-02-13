package com.develhope.spring.features.acquirente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente,Long> {
}
