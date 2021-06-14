package com.ApplyRestService.ApplyRestService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ApplyRestService.ApplyRestService.DTO.ApplyDTO;
import com.ApplyRestService.ApplyRestService.Entity.Apply;
import com.ApplyRestService.ApplyRestService.Entity.Notfications;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer>{
	
	List<Apply> findAll();
	
	Apply getById(Integer id);
	
	@Query(value = "select a from Apply a  where a.id =:applyId")
	Apply getApplyDetail( @Param("applyId") Integer id);

}
