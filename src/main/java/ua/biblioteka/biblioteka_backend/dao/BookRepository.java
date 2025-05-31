package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import ua.biblioteka.biblioteka_backend.entity.Book;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface BookRepository extends MongoRepository <Book, String>, BookCustomRepository {

Page<Book> findAll(Pageable pageable);
//List<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


}
