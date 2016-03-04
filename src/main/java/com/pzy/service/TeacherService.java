
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
import com.pzy.entity.Teacher;
import com.pzy.repository.TeacherRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class TeacherService {
     @Autowired
     private TeacherRepository teacherRepository;

 	public List<Teacher> findTop3() {
 		return teacherRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Teacher> findAll() {
         return (List<Teacher>) teacherRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Teacher> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Teacher> spec = new Specification<Teacher>() {
              public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Teacher> result = (Page<Teacher>) teacherRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Teacher> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Teacher> spec = new Specification<Teacher>() {
              public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Teacher> result = (Page<Teacher>) teacherRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			teacherRepository.delete(id);
		}
		public Teacher find(Long id){
			  return teacherRepository.findOne(id);
		}
		public void save(Teacher teacher){
			teacherRepository.save(teacher);
		}
}