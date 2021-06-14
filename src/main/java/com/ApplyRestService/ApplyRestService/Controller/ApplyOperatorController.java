package com.ApplyRestService.ApplyRestService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApplyRestService.ApplyRestService.DTO.ApplyDTO;
import com.ApplyRestService.ApplyRestService.DTO.NotficationsDTO;
import com.ApplyRestService.ApplyRestService.Entity.Apply;
import com.ApplyRestService.ApplyRestService.Repository.NotficationsRepository;
import com.ApplyRestService.ApplyRestService.Service.ApplyService;

@RestController
@RequestMapping("/api/operator/apply")
public class ApplyOperatorController {
	
    private final ApplyService applyService;
  
	
	public ApplyOperatorController(ApplyService applyService) {
		this.applyService = applyService;

	}
	
	@GetMapping("getAll")
	public ResponseEntity getAll() {
		return applyService.getApplyList();
	}
	@GetMapping("applyProfile/{id}")
	public ResponseEntity<Apply> getProfileApply(@PathVariable Integer id){
		return applyService.getApplyDetail(id);
	}
	@PostMapping("addApply")
	public ResponseEntity<Apply> addApplyPost(@RequestBody ApplyDTO applyDTO){
		return applyService.addApplyOperator(applyDTO);
	}
	@DeleteMapping("deleteApply/{id}")
	public ResponseEntity<Apply> deleteApply(@PathVariable Integer id){
		return applyService.delete(id);
	}
	@PutMapping("editApply/{id}")
	public ResponseEntity<Apply> updateApply(@PathVariable Integer id, @RequestBody ApplyDTO applyDTO){
		return applyService.update(id, applyDTO);
	}
	
	
	

}
