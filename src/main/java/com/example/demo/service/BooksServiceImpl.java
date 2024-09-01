package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Books;
import com.example.demo.repository.BooksRepository;

@Service
@Transactional
public class BooksServiceImpl implements BooksService{
	
	@Autowired
	BooksRepository booksrepository;
	
	@Override
	public Iterable<Books> SelectAll() {
		return booksrepository.findAll();
	}

	@Override
	public Optional<Books> SelectOneById(String isbn_code) {
		return booksrepository.findById(isbn_code);
	}

	@Override
	public void InsertBooks(Books books) {
		booksrepository.InsertData(books.getIsbn_code(), 
                                   books.getBook_title(),
                                   books.getAuthor(),
                                   books.getPublication_year(), 
                                   books.getPublisher());
	}

	@Override
	public void UpdateBooks(Books books) {
		booksrepository.UpdateData(books.getIsbn_code(), 
                                   books.getBook_title(),
                                   books.getAuthor(),
                                   books.getPublication_year(), 
                                   books.getPublisher());
	}
	
	@Override
	public void DeletebooksById(String isbn_code) { 
		booksrepository.deleteById(isbn_code);
	}
}