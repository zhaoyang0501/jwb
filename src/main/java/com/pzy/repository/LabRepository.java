package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Lab;
import com.pzy.entity.Patent;
public interface LabRepository extends PagingAndSortingRepository<Lab, Long>,JpaSpecificationExecutor<Lab>{
}

