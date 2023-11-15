package com.example.orderapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Goods name should not be empty")
    @Size(min = 3, message = "Goods name must be at least 3 characters")
    @Column(unique = true)
    private String name;

    @NotNull(message = "Goods price should not be null")
    private Double price;

    @NotNull(message = "Goods quantity should not be null")
    @Column(name = "quantity")
    private Integer availableQuantity;


}
