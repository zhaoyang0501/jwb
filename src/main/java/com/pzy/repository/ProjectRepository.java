package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.User;
import com.pzy.entity.Project;
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>,JpaSpecificationExecutor<Project>{
	public List<Project> findByUser(User user);
}

