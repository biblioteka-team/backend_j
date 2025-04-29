package ua.biblioteka.biblioteka_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "bestsellers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bestseller {
    @Id
    private String id;
//    @DBRef
    private List<String> bookIds;
}
