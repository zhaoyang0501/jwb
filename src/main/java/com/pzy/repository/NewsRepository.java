package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.pzy.entity.News;
public interface NewsRepository extends PagingAndSortingRepository<News, Long>,JpaSpecificationExecutor<News>{
}

