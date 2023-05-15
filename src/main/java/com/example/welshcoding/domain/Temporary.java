package com.example.welshcoding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "SEQ_TEMPORARYID_GENERATOR",
		sequenceName = "SEQ_TEMPORARYID",
		allocationSize = 1
		)
public class Temporary {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEMPORARYID_GENERATOR")
	private Long temporaryId;
	private String temporaryTitle;
	private String temporaryDetail;
	private String temporaryDate;
	
	@ManyToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;
}
