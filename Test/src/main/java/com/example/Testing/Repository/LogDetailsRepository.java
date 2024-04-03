package com.example.Testing.Repository;

import com.example.Testing.Entity.LogDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDetailsRepository extends JpaRepository<LogDetails, Long> {
}

