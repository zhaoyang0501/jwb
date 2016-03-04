
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.pzy.entity.Grade;
import com.pzy.entity.Timetable;
import com.pzy.repository.TimetableRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class TimetableService {
     @Autowired
     private TimetableRepository timetableRepository;

 	public List<Timetable> findByGradeAndWeek(Grade grade,Integer week) {
 		return timetableRepository.findByGradeAndWeek(grade, week);
 	}
     public List<Timetable> findAll() {
         return (List<Timetable>) timetableRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Timetable> findAll(final int pageNumber, final int pageSize,final Long id){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Timetable> spec = new Specification<Timetable>() {
              public Predicate toPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (id != null) {
                   predicate.getExpressions().add(cb.equal(root.get("grade").get("id").as(Long.class),id));
              }
              return predicate;
              }
         };
         Page<Timetable> result = (Page<Timetable>) timetableRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Timetable> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Timetable> spec = new Specification<Timetable>() {
              public Predicate toPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Timetable> result = (Page<Timetable>) timetableRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			timetableRepository.delete(id);
		}
		public Timetable find(Long id){
			  return timetableRepository.findOne(id);
		}
		public void save(Timetable timetable){
			timetableRepository.save(timetable);
		}
}