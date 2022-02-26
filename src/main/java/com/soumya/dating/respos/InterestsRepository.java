package com.soumya.dating.respos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.dating.entities.Interest;

public interface InterestsRepository extends JpaRepository<Interest, Integer> {

}
