package com.staj.CarCommerceApp.repositories;


import com.staj.CarCommerceApp.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
