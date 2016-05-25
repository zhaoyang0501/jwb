
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

import com.pzy.entity.Worker;
import com.pzy.repository.WorkerRepository;

@Service
public class WorkerService {
     @Autowired
     private WorkerRepository userRepository;
     public List<Worker> findAll() {
          return (List<Worker>) userRepository.findAll();
     }
     public Page<Worker> findAll(final int pageNumber, final int pageSize,final String userName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Worker> spec = new Specification<Worker>() {
                    @Override
                    public Predicate toPredicate(Root<Worker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (userName != null) {
                         predicate.getExpressions().add(cb.like(root.get("name").as(String.class), userName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Worker> result = (Page<Worker>) userRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(Long id){
          userRepository.delete(id);
     }
     public Worker find(Long id){
    	  return userRepository.findOne(id);
     }
     public void save(Worker Worker){
    	 userRepository.save(Worker);
     }
     public Worker login(String adminWorkerName,String password){
    	 List<Worker> adminWorkers=userRepository.findByUsernameAndPassword(adminWorkerName,password);
    	 return adminWorkers.size()==0?null:adminWorkers.get(0);
     }
}