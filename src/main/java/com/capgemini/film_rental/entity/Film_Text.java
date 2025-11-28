package com.capgemini.film_rental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "film_text")
public class Film_Text {
    @Id
    @Column(name = "film_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
}
