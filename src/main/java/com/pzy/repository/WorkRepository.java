package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.User;
import com.pzy.entity.Work;
public interface WorkRepository extends PagingAndSortingRepository<Work, Long>,JpaSpecificationExecutor<Work>{
	public List<Work> findByUser(User user);
}

