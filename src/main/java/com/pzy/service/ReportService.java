
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

import com.pzy.entity.Report;
import com.pzy.entity.User;
import com.pzy.repository.ReportRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class ReportService {
     @Autowired
     private ReportRepository reportRepository;

 	public List<Report> findTop3() {
 		return reportRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Report> findAll() {
         return (List<Report>) reportRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Report> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Report> spec = new Specification<Report>() {
              public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Report> result = (Page<Report>) reportRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Report> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Report> spec = new Specification<Report>() {
              public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Report> result = (Page<Report>) reportRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			reportRepository.delete(id);
		}
		public Report find(Long id){
			  return reportRepository.findOne(id);
		}
		public void save(Report report){
			reportRepository.save(report);
		}
		public List<Report> findByUser(User	 user){
			return reportRepository.findByUser(user);
		}
}