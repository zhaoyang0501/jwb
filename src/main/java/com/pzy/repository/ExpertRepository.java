package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Expert;
public interface ExpertRepository extends PagingAndSortingRepository<Expert, Long>,JpaSpecificationExecutor<Expert>{
}

