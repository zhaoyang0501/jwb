package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Score;
import com.pzy.entity.User;
public interface ScoreRepository extends PagingAndSortingRepository<Score, Long>,JpaSpecificationExecutor<Score>{
	public List<Score> findByCategoryNameAndUser(String name,User user);
}

