package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.biblioteka.biblioteka_backend.entity.Bestseller;

public interface BestsellerRepository extends MongoRepository<Bestseller, String> {

}
