package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BooksForm {
	/* 入力チェック */
	
	@NotBlank(message = "{NotBlank.message}")
	@Length(min= 13, max=13, message = "{isbn.Length.message}")
	private String isbn_code;
		
	@NotBlank(message = "{NotBlank.message}")
	@Length(min=1, max=150, message = "{Length.message}")
	private String book_title;
	
	@NotBlank(message = "{NotBlank.message}")
	@Length(min=1, max=60, message = "{Length.message}")
	private String author;
	
	@NotNull(message = "{NotNull.message}")
	private Integer publication_year;
	
	@NotBlank(message = "{NotBlank.message}")
	@Length(min=1, max=30, message = "{Length.message}")
	private String publisher;
	
	/* 「登録」か「更新」判定用 */
	private Boolean NewBooks;
	
}
