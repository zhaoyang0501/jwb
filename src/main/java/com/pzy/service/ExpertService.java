
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

import com.pzy.entity.Category;
import com.pzy.entity.Expert;
import com.pzy.repository.ExpertRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class ExpertService {
     @Autowired
     private ExpertRepository expertRepository;

 	public List<Expert> findTop3() {
 		return expertRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Expert> findAll() {
         return (List<Expert>) expertRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Expert> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Expert> spec = new Specification<Expert>() {
              public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Expert> result = (Page<Expert>) expertRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Expert> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Expert> spec = new Specification<Expert>() {
              public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (key != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+key+"%"));
              }
              if (category != null) {
                  predicate.getExpressions().add(cb.equal(root.get("category").as(Category.class), category));
              }
              return predicate;
              }
         };
         Page<Expert> result = (Page<Expert>) expertRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Expert> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Expert> spec = new Specification<Expert>() {
              public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Expert> result = (Page<Expert>) expertRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			expertRepository.delete(id);
		}
		public Expert find(Long id){
			  return expertRepository.findOne(id);
		}
		public void save(Expert expert){
			expertRepository.save(expert);
		}
}