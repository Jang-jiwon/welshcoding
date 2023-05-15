package com.example.welshcoding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy = "series")
	private List<Board> boards = new ArrayList<>();
	
	public void addBoard(Board board) {
		board.setSeries(this);
		this.boards.add(board);
	}
}
