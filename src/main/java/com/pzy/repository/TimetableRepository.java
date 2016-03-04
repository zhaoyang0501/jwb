package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Grade;
import com.pzy.entity.Timetable;
public interface TimetableRepository extends PagingAndSortingRepository<Timetable, Long>,JpaSpecificationExecutor<Timetable>{
	public List<Timetable> findByGradeAndWeek(Grade grade,Integer week);
}

