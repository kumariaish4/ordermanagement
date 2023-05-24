package com.order_management_system.repository;

import com.order_management_system.entities.Attender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttenderRepository extends JpaRepository<Attender,Long> {
}
