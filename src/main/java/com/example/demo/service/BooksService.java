package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Books;

public interface BooksService {
	
	/* 全件取得 */
	Iterable<Books> SelectAll();
	
	Optional<Books> SelectOneById(String isbn_code);
	
	/* 取得したデータを基に『書籍DB』に登録 */
    void InsertBooks(Books books);
    
    /* 取得したデータを基に『書籍DB』を更新 */
    void UpdateBooks(Books books);
    
    /* 取得したデータを基に『書籍DB』から削除 */
    void DeletebooksById(String isbn_code);
}
