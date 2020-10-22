package com.djajkoski.budgeteer.repository;

import com.djajkoski.budgeteer.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}
