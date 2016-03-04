
package com.pzy.service;

import java.util.Date;
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
import com.pzy.entity.Attence;
import com.pzy.repository.AttenceRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class AttenceService {
     @Autowired
     private AttenceRepository attenceRepository;

 	public List<Attence> findTop3() {
 		return attenceRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Attence> findAll() {
         return (List<Attence>) attenceRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public List<Attence> findAll(final User user,final Date begin,final Date end) {
         Specification<Attence> spec = new Specification<Attence>() {
              public Predicate toPredicate(Root<Attence> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              predicate.getExpressions().add(cb.equal(root.get("user").as(User.class), user));
              predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("workdate").as(Date.class), begin));
              predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("workdate").as(Date.class), end));
              return predicate;
              }
         };
         return attenceRepository.findAll(spec);
     }
     public Page<Attence> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Attence> spec = new Specification<Attence>() {
              public Predicate toPredicate(Root<Attence> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("user").get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Attence> result = (Page<Attence>) attenceRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Attence> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Attence> spec = new Specification<Attence>() {
              public Predicate toPredicate(Root<Attence> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Attence> result = (Page<Attence>) attenceRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			attenceRepository.delete(id);
		}
		public Attence find(Long id){
			  return attenceRepository.findOne(id);
		}
		public Attence findByUser(User user){
			  List<Attence> lists=attenceRepository.findByUser(user);
			  if(lists.size()==0) return null;
			  else return
				 lists.get(0);
		}
		public void save(Attence attence){
			attenceRepository.save(attence);
		}
}