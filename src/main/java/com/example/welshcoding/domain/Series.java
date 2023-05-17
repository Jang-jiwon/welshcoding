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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "SEQ_SERIESID_GENERATOR",
		sequenceName = "SEQ_SERIESID",
		allocationSize = 1
		)
public class Series {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERIESID_GENERATOR")
	private Long seriesId;
	private String seriesName;
	/*시리즈 생성일, 마지막 수정일 컬럼 추가*/
	private String createDate;
	private String updateDate;
	private String seriesImgUrl;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBERID")
	private Member member;
	
	@OneToMany(mappedBy = "series")
	private List<Board> boards = new ArrayList<>();
	
	public void addSeries(Board board) {
		board.setSeries(this);
		this.boards.add(board);
	}
}
