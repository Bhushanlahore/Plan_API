package com.service;

import java.util.List;

import com.entity.PlanCategory;


public interface PlanCategoryService {

	//add planCategory
	public boolean addPlanCategory(PlanCategory pc);
	
	//getCategoryBy id
	public boolean getCategory(Integer categoryId);
	
	//get all categories
	public List<PlanCategory> getAllPlanCategories();
	
	//delete category by id
	public boolean deleteById(Integer categoryId);
	
}
