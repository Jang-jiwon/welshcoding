package com.example.welshcoding.domain;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Sns {
	private String userGithub;
	private String userTwitter;
	private String userFacebook;
	private String userHomepage;
	
	public Sns(String userGithub, String userTwitter, String userFacebook, String userHomepage) {
		super();
		this.userGithub = userGithub;
		this.userTwitter = userTwitter;
		this.userFacebook = userFacebook;
		this.userHomepage = userHomepage;
	}
	
	// 파라미터 있는 생성자를 선언 시,
	// 기본 생성자 필수적으로 선언 해줘야 한다.
	public Sns() {}
	
	
}
