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
		name = "SEQ_COMMENTID_GENERATOR",
		sequenceName = "SEQ_COMMENTID",
		allocationSize = 1
		)
public class Comments {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMMENTID_GENERATOR")
	private Long commentId;
	private String commentCont;
	
	@ManyToOne
	@JoinColumn(name = "BOARDID")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;
}
