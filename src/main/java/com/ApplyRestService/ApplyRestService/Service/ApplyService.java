package com.ApplyRestService.ApplyRestService.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ApplyRestService.ApplyRestService.DTO.ApplyDTO;
import com.ApplyRestService.ApplyRestService.DTO.NotficationsDTO;
import com.ApplyRestService.ApplyRestService.Entity.Apply;
import com.ApplyRestService.ApplyRestService.Entity.Notfications;


public interface ApplyService {

	ResponseEntity<List<Apply>> getApplyList();

	Apply getById(Integer id);

	ResponseEntity<Apply> addApplyCitizen(ApplyDTO applyDTO);

	ResponseEntity<Apply> delete(Integer id);

	ResponseEntity getApplyDetail(Integer id);

	ResponseEntity<Apply> update(Integer id, ApplyDTO applyDTO);

	ResponseEntity<Apply> addApplyOperator(ApplyDTO applyDTO);

	
	
}
