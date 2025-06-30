package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCustomRepositoryImpl implements BookCustomRepository{

    private final MongoTemplate mongoTemplate;

    public BookCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Book> searchBooks(String title, String author, Category category, List<Subcategory> subcategories, BigDecimal min, BigDecimal max, String publisher, Integer ageRestriction, Language language, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            criteriaList.add(Criteria.where("title").regex(title, "i"));
        }

        if (author != null && !author.isBlank()) {
            criteriaList.add(Criteria.where("author").regex(author, "i")); // масив авторів
        }

        if (publisher != null && !publisher.isBlank()) {
            criteriaList.add(Criteria.where("publisher").regex(publisher, "i")); // масив авторів
        }

        if (ageRestriction != null) {
           criteriaList.add(Criteria.where("ageRestriction").lte(ageRestriction));
       }


        if (category != null) {
            criteriaList.add(Criteria.where("category").in(category));
        }

        if (subcategories != null && !subcategories.isEmpty()) {
            criteriaList.add(Criteria.where("subcategories").in(subcategories));

        }
        if (min != null && max != null) {
            criteriaList.add(Criteria.where("price").gte(min).lte(max));
        } else if (min != null) {
            criteriaList.add(Criteria.where("price").gte(min));
        } else if (max != null) {
            criteriaList.add(Criteria.where("price").lte(max));
        }

//        if (price != null) {
//            criteriaList.add(Criteria.where("price").lte(price));
//        }

        if (language != null) {
            criteriaList.add(Criteria.where("language").is(language));
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }

        Query query = new Query(finalCriteria);
        long total = mongoTemplate.count(query, Book.class);         // <--- загальна кількість документів
        List<Book> books = mongoTemplate.find(query.with(pageable), Book.class); // <--- дані з пагінацією

        return new PageImpl<>(books, pageable, total);


    }


}
