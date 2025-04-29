package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.biblioteka.biblioteka_backend.entity.NewArrival;

public interface NewArrivalRepository extends MongoRepository<NewArrival, String> {
}
