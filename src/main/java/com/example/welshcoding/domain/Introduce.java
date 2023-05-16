package com.example.welshcoding.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "SEQ_INTRODUCEID_GENERATOR",
		sequenceName = "SEQ_INTRODUCEID",
		allocationSize = 1
		)
public class Introduce {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTRODUCEID_GENERATOR")
	private Long introduceId;
	private String content;
	
	@OneToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;
	
}
