package com.ApplyRestService.ApplyRestService.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ApplyRestService.ApplyRestService.DTO.ApplyDTO;
import com.ApplyRestService.ApplyRestService.DTO.NotficationsDTO;
import com.ApplyRestService.ApplyRestService.Entity.Apply;
import com.ApplyRestService.ApplyRestService.Entity.Notfications;
import com.ApplyRestService.ApplyRestService.Repository.ApplyRepository;
import com.ApplyRestService.ApplyRestService.Repository.NotficationsRepository;
import com.ApplyRestService.ApplyRestService.Service.ApplyService;

@Service 
@Transactional
public class ApplyServiceImpl implements ApplyService{

	private final ApplyRepository applyRepository;
	private final NotficationsRepository notficationsRepository;
	
	public ApplyServiceImpl(ApplyRepository applyRepository,NotficationsRepository notficationsRepository) {
		this.applyRepository = applyRepository;
		this.notficationsRepository=notficationsRepository;
	
	}

	@Override
	public ResponseEntity getApplyList() {
		List<Apply> lists = applyRepository.findAll();
		List<ApplyDTO> dtsList = new ArrayList<>();
		
		if(lists != null && lists.size()>0) {
			for(Apply a :lists) {

				ApplyDTO appDTO = new ApplyDTO();
				appDTO.setId(a.getId());
				appDTO.setStatus(a.getStatus());
				appDTO.setText(a.getText());

				List<Notfications> ntsLists = notficationsRepository.findAllByApplyId(a.getId());

				List<NotficationsDTO> notList=new ArrayList<>();
				
				for(Notfications n : ntsLists) {
					NotficationsDTO notDTO=new NotficationsDTO();
					notDTO.setId(n.getId());
					notDTO.setNote(n.getNote());
					
					notList.add(notDTO);
					
				}
				
				appDTO.setNotficationsDTO(notList);
				dtsList.add(appDTO);
				
			}
			return ResponseEntity.ok(dtsList);
			
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Override
	public Apply getById(Integer id) {
		return applyRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<Apply> addApplyCitizen(ApplyDTO applyDTO) {
		Apply apply = new Apply();
		apply.setText(applyDTO.getText());
		apply.setStatus(applyDTO.getStatus());
		applyRepository.save(apply);
	   	
			Notfications notfications = new Notfications();
			
		    notfications.setApplyId(apply);
			
			notficationsRepository.save(notfications);
		
		
		return ResponseEntity.ok(apply);
	}
	@Override
	public ResponseEntity<Apply> addApplyOperator(ApplyDTO applyDTO) {
		Apply apply = new Apply();
		
		apply.setText(applyDTO.getText());
		apply.setStatus(applyDTO.getStatus());
		applyRepository.save(apply);
		List<NotficationsDTO> notficationsDTO = applyDTO.getNotficationsDTO();
		
		for(NotficationsDTO n : notficationsDTO) {
			
			Notfications notfications = new Notfications();
			
		    notfications.setApplyId(apply);
			notfications.setNote(n.getNote());
			
			notficationsRepository.save(notfications);
		}
		
		return ResponseEntity.ok(apply);
	}
	@Override
	public ResponseEntity<Apply> delete(Integer id) {
		Optional<Apply> applyOptional=applyRepository.findById(id);
		if (applyOptional.isPresent()){
			applyRepository.deleteById(id);
			return ResponseEntity.ok(applyOptional.get());
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity getApplyDetail(Integer id) {

		Apply apply = applyRepository.getApplyDetail(id);
		List<ApplyDTO> dtsList = new ArrayList<>();
		
		if(apply == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
			

				ApplyDTO appDTO = new ApplyDTO();
				appDTO.setId(apply.getId());
				appDTO.setStatus(apply.getStatus());
				appDTO.setText(apply.getText());

				List<Notfications> ntsLists = notficationsRepository.findAllByApplyId(apply.getId());

				List<NotficationsDTO> notList=new ArrayList();
				
				for(Notfications n : ntsLists) {
					NotficationsDTO notDTO=new NotficationsDTO();
					notDTO.setId(n.getId());
					notDTO.setNote(n.getNote());
					
					notList.add(notDTO);
					
				}
				
				appDTO.setNotficationsDTO(notList);
				dtsList.add(appDTO);
				
			
			return ResponseEntity.ok(dtsList);
			
		
	
	}


	@Override
	public ResponseEntity<Apply> update(Integer id, ApplyDTO applyDTO) {
		Apply apply = applyRepository.getById(id);
		if(apply != null) {
		   apply.setText(applyDTO.getText());
		   apply.setStatus(applyDTO.getStatus());
		}else {
			return new ResponseEntity<Apply>(HttpStatus.NO_CONTENT);
		}
		applyRepository.save(apply);
		
        List<NotficationsDTO> notficationsDTO = applyDTO.getNotficationsDTO();
		
		for(NotficationsDTO n : notficationsDTO) {
			
			Optional<Notfications> notfications = notficationsRepository.findById(n.getId());
			
			if(notfications.isPresent()) {
				notfications.get().setApplyId(apply);
				notfications.get().setNote(n.getNote());
			}
			else {
				return new ResponseEntity<Apply>(HttpStatus.NO_CONTENT);
			}
		    
			notficationsRepository.save(notfications.get());
		}
		return ResponseEntity.ok(apply);
	
	
	}
	
}
