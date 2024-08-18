package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data               // Getter,Setterが不要になる
@NoArgsConstructor  // デフォルトコンストラクターの自動生成
@AllArgsConstructor // 全フィールドに対する初期化値を引数に取るコンストラクタを自動生成

public class Books {
	@Id	
	private String isbn_code;
	
	private String book_title;
	private String author;
	private Integer publication_year;
	private String publisher;
	
    @CreatedDate
	private LocalDateTime created_at;
	
    @LastModifiedDate
    private LocalDateTime update_at;
    
	private Integer created_id;

}