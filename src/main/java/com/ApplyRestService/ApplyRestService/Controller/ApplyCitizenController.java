package com.ApplyRestService.ApplyRestService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApplyRestService.ApplyRestService.DTO.ApplyDTO;
import com.ApplyRestService.ApplyRestService.Entity.Apply;
import com.ApplyRestService.ApplyRestService.Service.ApplyService;

@RestController
@RequestMapping("/api/citizen/apply")
public class ApplyCitizenController {

	private final ApplyService applyService;
	
	public ApplyCitizenController(ApplyService applyService) {
		this.applyService = applyService;
	}
	
	@GetMapping("getAll")
	public ResponseEntity getAll() {
		return applyService.getApplyList();
	}
	@PostMapping("addApply")
	public ResponseEntity<Apply> addApplyPost(@RequestBody ApplyDTO applyDTO){
		return applyService.addApplyCitizen(applyDTO);
	}
}
