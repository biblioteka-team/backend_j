package ua.biblioteka.biblioteka_backend.dao;

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
    public List<Book> searchBooks(String title, String author, Category category, Subcategory subcategory, BigDecimal price, Language language) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            criteriaList.add(Criteria.where("title").regex(title, "i"));
        }

        if (author != null && !author.isBlank()) {
            criteriaList.add(Criteria.where("author").regex(author, "i")); // масив авторів
        }

        if (category != null) {
            criteriaList.add(Criteria.where("category").in(category));
        }

        if (subcategory != null) {
            criteriaList.add(Criteria.where("subcategory").in(subcategory));

        }

        if (price != null) {
            criteriaList.add(Criteria.where("price").lte(price));
        }

        if (language != null) {
            criteriaList.add(Criteria.where("language").is(language));
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }

        Query query = new Query(finalCriteria);
        return mongoTemplate.find(query, Book.class);
    }

}
