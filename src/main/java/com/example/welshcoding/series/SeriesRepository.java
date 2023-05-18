package com.example.welshcoding.series;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.BoardDTO;
import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeriesRepository {
	
	private final EntityManager em;

	public List<Series> findSeriesAll(Long memberId) {
		String query = "select s from Series s INNER JOIN s.member m"
				+ " where m.memberId = :memberid";
		return em.createQuery(query, Series.class)
				 .setParameter("memberid", memberId)
				 .getResultList();
	}

	public List<BoardDTO> findBoardsBySeries(Long seriesId) {

		String query = "select new com.example.welshcoding.domain.BoardDTO(b.boardId, s.seriesId, s.seriesName, b.boardTitle, b.boardTag, b.boardDate, b.boardCont, b.boardLike, b.thumbnailPath)"
				+ " from Board b INNER JOIN b.series s "
				+ " where s.seriesId = :seriesid order by b.boardDate, b.boardId";
		return em.createQuery(query, BoardDTO.class)
				 .setParameter("seriesid", seriesId)
				 .getResultList();
	}

	public Series findById(Long seriesId) {
		return em.find(Series.class, seriesId);
	}

	@Transactional
	public void deleteSeries(Series series) {
		 em.remove(series);
	}

}
