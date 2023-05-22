package com.example.welshcoding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "SEQ_BOARDID_GENERATOR",
		sequenceName = "SEQ_BOARDID",
		allocationSize = 1
		)
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOARDID_GENERATOR")
	private Long boardId;
	private String boardTitle;
	private String boardTag;
	private String boardDate;
//	@Column(length = 500)
	@Lob
	private String boardCont;
	private String boardLike;
	
	@Lob
	private String thumbnailPath;
	private String boardIntro;
	
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name = "SERIESID")
	private Series series;
	
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBERID")
	private Member member;
	
	@OneToMany(mappedBy = "board" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comments> comments = new ArrayList<>();
	
	public void addComments(Comments comment) {
		comment.setBoard(this);
		this.comments.add(comment);
	}
	
	@OneToMany(mappedBy = "board" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tags> tags = new ArrayList<>();
	public void addTags(Tags tag) {
		tag.setBoard(this);
		this.tags.add(tag);
	}
	
}
