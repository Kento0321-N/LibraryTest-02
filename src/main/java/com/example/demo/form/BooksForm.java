package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BooksForm {
	/* 入力チェック */
	
	@NotBlank
	@Length(min=13, max=13)
	private String isbn_code;

	@NotBlank
	@Length(min=1, max=150) 
	private String book_title;
	
	@NotBlank
	@Length(min=1, max=60) 
	private String author;
	
	@NotNull
	private Integer publication_year;
	
	@NotBlank
	private String publisher;
	
	/* 「登録」か「更新」判定用 */
	private Boolean NewBooks;
	
}
