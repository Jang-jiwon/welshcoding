package com.example.welshcoding.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Introduce {
	@Id
	private Long introduceId = 1L; // 고정된 값을 할당

	private String content;

	@OneToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;


}
