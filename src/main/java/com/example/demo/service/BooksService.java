package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Books;

public interface BooksService {
	
	/* 全件取得 */
	Iterable<Books> SelectAll();
	
	Optional<Books> SelectOneById(String isbn_code);
	
	/* 取得したデータをDBにInsertする */
    void InsertBooks(Books books);
    
    /* データを更新する */
    void UpdateBooks(Books books);
    
    /* データを削除する */
    void DeletebooksById(String isbn_code);
}
