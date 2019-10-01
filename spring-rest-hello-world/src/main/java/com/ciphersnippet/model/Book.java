package com.ciphersnippet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String author;
	private BigDecimal price;

	// setters, getters, constructors...

	public Book(String name, String author, BigDecimal price) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
	}

}
