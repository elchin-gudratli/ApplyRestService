package com.ApplyRestService.ApplyRestService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ApplyRestService.ApplyRestService.Entity.Notfications;



public interface NotficationsRepository extends JpaRepository<Notfications, Integer> {

      @Query(value="select n from Notfications n where n.applyId.id=:applyId")
	  List<Notfications> findAllByApplyId(@Param("applyId") Integer id);
 
	
	

}
