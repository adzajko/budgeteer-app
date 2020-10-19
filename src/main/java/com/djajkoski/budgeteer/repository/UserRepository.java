package com.djajkoski.budgeteer.repository;

import com.djajkoski.budgeteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByUsername(String username);

  Optional<User> findById(long id);

  @Transactional
  void deleteById(long id);

  User findByUsername(String username);
}
