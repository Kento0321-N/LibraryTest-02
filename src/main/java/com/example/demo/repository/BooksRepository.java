package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Books;

	//当クラスはDBへのデータ操作を行うクラス(=Repositoryの役割)

public interface  BooksRepository extends CrudRepository<Books, String> {
	@Modifying
	@Query(value = "INSERT INTO books(isbn_code,book_title,author,publication_year,publisher)"
			     + " VALUES(:isbn_code, :book_title, :author, :publication_year, :publisher)")
	void InsertData(@Param("isbn_code") String isbn_code, 
			       @Param("book_title") String book_title, 
			       @Param("author") String author,
			       @Param("publication_year") Integer publication_year,
			       @Param("publisher") String publisher);
	
	@Modifying
	@Query(value = "UPDATE books SET book_title = :book_title,author = :author,"
		         + "publication_year = :publication_year,publisher = :publisher"
			     + " WHERE isbn_code = :isbn_code")
    void UpdateData(@Param("isbn_code") String isbn_code, 
		       @Param("book_title") String book_title, 
		       @Param("author") String author,
		       @Param("publication_year") Integer publication_year,
		       @Param("publisher") String publisher);
	
	//☆今後の検討☆
	//DB登録、更新時に登録時間や更新時間がNullでエラーになるため、
	//上記のようにSQLを直接記載、SQLを記載しないで良い方法があるかどうかは検討してみること。
	//※継承している「CrudRepository」で、削除は問題なくできるので継承の記載はそのままにしている。
}

