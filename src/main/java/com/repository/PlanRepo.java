package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
