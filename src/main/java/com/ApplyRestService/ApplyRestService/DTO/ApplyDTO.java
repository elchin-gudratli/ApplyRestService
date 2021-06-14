package com.ApplyRestService.ApplyRestService.DTO;

import java.util.List;

public class ApplyDTO {

	private Integer id;
	private String text;
	private Boolean status;
    private List<NotficationsDTO> notficationsDTO;
    
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<NotficationsDTO> getNotficationsDTO() {
		return notficationsDTO;
	}
	public void setNotficationsDTO(List<NotficationsDTO> notficationsDTO) {
		this.notficationsDTO = notficationsDTO;
	}
	
	
}
