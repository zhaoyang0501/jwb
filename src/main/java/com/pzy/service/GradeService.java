
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
import com.pzy.repository.GradeRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class GradeService {
     @Autowired
     private GradeRepository gradeRepository;

 	public List<Grade> findTop3() {
 		return gradeRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Grade> findAll() {
         return (List<Grade>) gradeRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Grade> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Grade> spec = new Specification<Grade>() {
              public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Grade> result = (Page<Grade>) gradeRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Grade> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Grade> spec = new Specification<Grade>() {
              public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Grade> result = (Page<Grade>) gradeRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			gradeRepository.delete(id);
		}
		public Grade find(Long id){
			  return gradeRepository.findOne(id);
		}
		public void save(Grade grade){
			gradeRepository.save(grade);
		}
}