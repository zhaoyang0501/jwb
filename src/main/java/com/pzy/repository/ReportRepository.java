package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Report;
import com.pzy.entity.User;
public interface ReportRepository extends PagingAndSortingRepository<Report, Long>,JpaSpecificationExecutor<Report>{
	public List<Report> findByUser(User user);
}

