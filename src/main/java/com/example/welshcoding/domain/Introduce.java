package com.example.welshcoding.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SequenceGenerator(
		name = "SEQ_INTRODUCEID_GENERATOR",
		sequenceName = "SEQ_INTRODUCEID",
		allocationSize = 1
)
public class Introduce {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTRODUCEID_GENERATOR")
	private Long introduceId; // 고정된 값을 할당

	private String content;

	@OneToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;


}
