
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

import com.pzy.entity.News;
import com.pzy.repository.NewsRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class NewsService {
     @Autowired
     private NewsRepository newsRepository;

 	public List<News> findTop3() {
 		return newsRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<News> findAll() {
         return (List<News>) newsRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<News> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<News> spec = new Specification<News>() {
              public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<News> result = (Page<News>) newsRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<News> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<News> spec = new Specification<News>() {
              public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<News> result = (Page<News>) newsRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			newsRepository.delete(id);
		}
		public News find(Long id){
			  return newsRepository.findOne(id);
		}
		public void save(News news){
			newsRepository.save(news);
		}
}