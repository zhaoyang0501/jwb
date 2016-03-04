
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
import com.pzy.entity.Paper;
import com.pzy.repository.PaperRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class PaperService {
     @Autowired
     private PaperRepository paperRepository;

 	public List<Paper> findTop3() {
 		return paperRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Paper> findAll() {
         return (List<Paper>) paperRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Paper> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Paper> spec = new Specification<Paper>() {
              public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Paper> result = (Page<Paper>) paperRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Paper> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Paper> spec = new Specification<Paper>() {
              public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
         Page<Paper> result = (Page<Paper>) paperRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Paper> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Paper> spec = new Specification<Paper>() {
              public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Paper> result = (Page<Paper>) paperRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			paperRepository.delete(id);
		}
		public Paper find(Long id){
			  return paperRepository.findOne(id);
		}
		public void save(Paper paper){
			paperRepository.save(paper);
		}
}