
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

import com.pzy.entity.User;
import com.pzy.entity.Work;
import com.pzy.repository.WorkRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class WorkService {
     @Autowired
     private WorkRepository workRepository;

 	public List<Work> findTop3() {
 		return workRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Work> findAll() {
         return (List<Work>) workRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Work> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Work> spec = new Specification<Work>() {
              public Predicate toPredicate(Root<Work> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Work> result = (Page<Work>) workRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Work> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Work> spec = new Specification<Work>() {
              public Predicate toPredicate(Root<Work> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Work> result = (Page<Work>) workRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			workRepository.delete(id);
		}
		public Work find(Long id){
			  return workRepository.findOne(id);
		}
		public Work findByUser(User user){
			  List<Work> lists=workRepository.findByUser(user);
			  if(lists.size()==0) return null;
			  else return
				 lists.get(0);
		}
		public void save(Work work){
			workRepository.save(work);
		}
}