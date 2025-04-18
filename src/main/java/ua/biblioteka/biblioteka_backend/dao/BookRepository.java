package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.biblioteka.biblioteka_backend.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository <Book, String> {
    @Query("{ $text: { $search: ?0 } }")
    List<Book> searchByText(String text);
    List<Book> findByCategory(String category);
}
