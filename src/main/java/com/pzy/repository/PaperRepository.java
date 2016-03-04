package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Paper;
public interface PaperRepository extends PagingAndSortingRepository<Paper, Long>,JpaSpecificationExecutor<Paper>{
}

