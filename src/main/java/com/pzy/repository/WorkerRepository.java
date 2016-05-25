package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.User;
import com.pzy.entity.Worker;
public interface WorkerRepository extends PagingAndSortingRepository<Worker, Long>,JpaSpecificationExecutor<Worker>{
    public List<Worker> findByIdAndPassword(String id,String password);
    public List<Worker> findByUsernameAndPassword(String userName,String password);
	public List<Worker> findByUsername(String userName);
}

