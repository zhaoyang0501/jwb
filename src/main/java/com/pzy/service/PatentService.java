
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
import com.pzy.entity.Patent;
import com.pzy.repository.PatentRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class PatentService {
     @Autowired
     private PatentRepository patentRepository;

 	public List<Patent> findTop3() {
 		return patentRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Patent> findAll() {
         return (List<Patent>) patentRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Patent> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Patent> spec = new Specification<Patent>() {
              public Predicate toPredicate(Root<Patent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Patent> result = (Page<Patent>) patentRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Patent> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Patent> spec = new Specification<Patent>() {
              public Predicate toPredicate(Root<Patent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
         Page<Patent> result = (Page<Patent>) patentRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Patent> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Patent> spec = new Specification<Patent>() {
              public Predicate toPredicate(Root<Patent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Patent> result = (Page<Patent>) patentRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			patentRepository.delete(id);
		}
		public Patent find(Long id){
			  return patentRepository.findOne(id);
		}
		public void save(Patent patent){
			patentRepository.save(patent);
		}
}