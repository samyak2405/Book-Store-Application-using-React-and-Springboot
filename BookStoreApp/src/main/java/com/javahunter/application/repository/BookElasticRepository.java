package com.javahunter.application.repository;

import com.javahunter.application.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookElasticRepository extends ElasticsearchRepository<Book,Integer> {
}
