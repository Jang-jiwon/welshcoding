package com.example.welshcoding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
	@Lob
	private String profileImg;
//	private String userBirth;
//	private String userGender;
//	private String userPhone;
	@Embedded
	private Sns sns;
//	private String tagsString;

	// 일대다 연결에서 Member 엔티티에 대한 모든 작업이 자식 엔티티에 적용되도록 옵션 추가)
	// cascade = CascadeType.ALL, orphanRemoval = true

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Board> boards = new ArrayList<>();
	
	public void addBoard(Board board) {
		board.setMember(this);
		this.boards.add(board);
	}

	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Temporary> temporaries = new ArrayList<>();
	
	public void addTemporary(Temporary temporary) {
		temporary.setMember(this);
		this.temporaries.add(temporary);
	}
	
	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private Introduce introduce;
	
	/*멤버가 가지고 있는 시리즈 출력을 위해 추가 - kdy*/
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Series> serieses = new ArrayList<>();
	
	public void addSeries(Series series) {
		series.setMember(this);
		this.serieses.add(series);
	}
	
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tags> tags = new ArrayList<>();
	public void addTags(Tags tag) {
		tag.setMember(this);
		this.tags.add(tag);
	}
	
}
