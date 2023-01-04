package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constants.AppConstants;
import com.entity.Plan;
import com.props.AppProperties;
import com.service.imp.PlanServiceImpl;

import net.bytebuddy.asm.Advice.This;

@RestController
public class PlanController {


	private PlanServiceImpl planServiceImpl;
	
	private Map<String, String> messages = new HashMap<>();
	
	public PlanController(PlanServiceImpl planServiceImpl, AppProperties appProperties) {
		this.planServiceImpl = planServiceImpl;
		this.messages = appProperties.getMessages();
		
		System.out.println(this.messages);
	}
	

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> PlanCategories() {

		Map<Integer, String> categoriesMap = planServiceImpl.getPlanCategories();

		return new ResponseEntity<>(categoriesMap, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> addPlan(@RequestBody Plan plan) {

		String msg = AppConstants.EMPTY_STR;

		boolean isSaved = planServiceImpl.addPlan(plan);

		if (isSaved)
			msg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_SAVE_FAIL);

		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/getAllPlans")
	public ResponseEntity<List<Plan>> getAllPlans() {

		List<Plan> allPlans = planServiceImpl.getAllPlans();

		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan planById = planServiceImpl.getPlanById(planId);

		return new ResponseEntity<>(planById, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		String msg = "";

		boolean updated = planServiceImpl.updatePlan(plan);

		if (updated)
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String msg = "";
		boolean isDelete = planServiceImpl.deletePlanById(planId);

		if (isDelete)
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{plandId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status){
		
		String msg ="";
		
		boolean isplanStatusChange = planServiceImpl.planStatusChange(planId, status);
		
		if(isplanStatusChange) {
			msg= messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
		}else {
			msg= messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		} 
		
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
}
