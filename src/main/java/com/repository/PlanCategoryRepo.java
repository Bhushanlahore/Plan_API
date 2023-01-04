package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

	//PlanCategory findByCategoryName(String categoryName);

}
