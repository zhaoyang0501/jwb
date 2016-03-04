
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
import com.pzy.entity.Lab;
import com.pzy.repository.LabRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class LabService {
     @Autowired
     private LabRepository labRepository;

 	public List<Lab> findTop3() {
 		return labRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Lab> findAll() {
         return (List<Lab>) labRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Lab> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Lab> spec = new Specification<Lab>() {
              public Predicate toPredicate(Root<Lab> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Lab> result = (Page<Lab>) labRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Lab> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Lab> spec = new Specification<Lab>() {
              public Predicate toPredicate(Root<Lab> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
         Page<Lab> result = (Page<Lab>) labRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Lab> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Lab> spec = new Specification<Lab>() {
              public Predicate toPredicate(Root<Lab> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Lab> result = (Page<Lab>) labRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			labRepository.delete(id);
		}
		public Lab find(Long id){
			  return labRepository.findOne(id);
		}
		public void save(Lab lab){
			labRepository.save(lab);
		}
}