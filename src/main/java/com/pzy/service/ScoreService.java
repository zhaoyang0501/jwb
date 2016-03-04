
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

import com.pzy.entity.Score;
import com.pzy.entity.User;
import com.pzy.repository.ScoreRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class ScoreService {
     @Autowired
     private ScoreRepository scoreRepository;

 	public List<Score> findTop3() {
 		return scoreRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Score> findAll() {
         return (List<Score>) scoreRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Score> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Score> spec = new Specification<Score>() {
              public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("user").get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Score> result = (Page<Score>) scoreRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Score> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Score> spec = new Specification<Score>() {
              public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Score> result = (Page<Score>) scoreRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			scoreRepository.delete(id);
		}
		public Score find(Long id){
			  return scoreRepository.findOne(id);
		}
		public void save(Score score){
			scoreRepository.save(score);
		}
		public List<Score> findByCategoryName(String name,User user){
			  return scoreRepository.findByCategoryNameAndUser(name,user);
		}
}