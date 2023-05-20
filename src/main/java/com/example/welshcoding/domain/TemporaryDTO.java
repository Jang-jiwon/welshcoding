package com.example.welshcoding.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TemporaryDTO {
	private Long temporaryId;
	private String temporaryTitle;
	private String temporaryDetail;
	private String temporaryDate;
	
	public TemporaryDTO(Long temporaryId, String temporaryTitle, String temporaryDetail, String temporaryDate) {
		this.temporaryId = temporaryId;
		this.temporaryTitle = temporaryTitle;
		this.temporaryDetail = temporaryDetail;
		this.temporaryDate = temporaryDate;
	}
	 public Temporary toEntity(){
	        return new Temporary();
	    }
	

}
