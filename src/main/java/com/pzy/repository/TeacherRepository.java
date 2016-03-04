package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Teacher;
import com.pzy.entity.User;
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long>,JpaSpecificationExecutor<Teacher>{
  
}

