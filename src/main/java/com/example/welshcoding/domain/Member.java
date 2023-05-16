package com.example.welshcoding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "SEQ_MEMBERID_GENERATOR",
		sequenceName = "SEQ_MEMBERID",
		allocationSize = 1
		)
public class Member {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBERID_GENERATOR")
	private Long memberId;
	private String userEmail;
	private String userPwd;
	private String velogPageName;
	private String userName;
	private String userBio;
	private String profileImg;
	private String userBirth;
	private String userGender;
	private String userPhone;
	@Embedded
	private Sns sns;
	
	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();
	
	public void addBoard(Board board) {
		board.setMember(this);
		this.boards.add(board);
	}
	
	@OneToMany(mappedBy = "member")
	private List<Comments> comments = new ArrayList<>();
	
	public void addComments(Comments comment) {
		comment.setMember(this);
		this.comments.add(comment);
	}
	
	@OneToMany(mappedBy = "member")
	private List<Temporary> temporaries = new ArrayList<>();
	
	public void addTemporary(Temporary temporary) {
		temporary.setMember(this);
		this.temporaries.add(temporary);
	}
	
	@OneToOne(mappedBy = "member")
	private Introduce introduce;
}
