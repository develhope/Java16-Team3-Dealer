package com.develhope.spring.features.ordiniAcquisti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto,Long> {
}
