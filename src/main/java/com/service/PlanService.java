package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();
	
	public boolean addPlan(Plan plan);
	
	public Plan getPlanById(Integer planId);
	
	public List<Plan> getAllPlans();
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlanById(Integer pladId);
	
	public boolean planStatusChange(Integer planId, String status);
}
