package com.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Plan;
import com.entity.PlanCategory;
import com.repository.PlanCategoryRepo;
import com.repository.PlanRepo;
import com.service.PlanService;

@Service 
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> categories = planCategoryRepo.findAll();

		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});

		return categoryMap;
	}

	@Override
	public boolean addPlan(Plan plan) {

		Plan saved = planRepo.save(plan);

		/*fresher
		 * if(saved.getPlanId()!=null) { 
		 * return true; 
		 * }else 
		 * { return false; }
		 */
		//1 year experiance
//		return saved.getPlanId()!=null ? true:false;
		
		return saved.getPlanId()!=null;
		
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent())
			return findById.get();
		
		return null;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepo.findAll();
	}

	@Override
	public boolean updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		Plan saved = planRepo.save(plan); //(upsert method)
		
		return saved.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer pladId) {
		
		boolean status = false;
		
		try {
			planRepo.deleteById(pladId);
			status = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 	
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		// TODO Auto-generated method stub
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		
		return false;
	}

}
