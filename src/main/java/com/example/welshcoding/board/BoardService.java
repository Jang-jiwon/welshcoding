package com.example.welshcoding.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	
	public List<Board> findBoards() {
		return boardRepository.findAll();
	}
	
	@Transactional
	public void insertData(Board board)throws IllegalAccessException {
		System.out.println("===========insertData==============");
		boardRepository.save(board);
	}
	
}
