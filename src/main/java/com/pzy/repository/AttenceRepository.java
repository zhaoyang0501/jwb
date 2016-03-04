package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.User;
import com.pzy.entity.Attence;
public interface AttenceRepository extends PagingAndSortingRepository<Attence, Long>,JpaSpecificationExecutor<Attence>{
	public List<Attence> findByUser(User user);
}

