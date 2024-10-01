package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.BooksForm;
import com.example.demo.model.Books;
import com.example.demo.service.BooksService;

@RequestMapping("/books")
@Controller
public class Bookscontroller {
	
	@Autowired
	BooksService service;
	
	@ModelAttribute
	public BooksForm setUpForm() {
		BooksForm form = new BooksForm();
		return form;
	}
	
	/* 一覧画面表示 */
	@GetMapping
	public String ShowList(BooksForm booksForm, Model model) {
		Iterable<Books> books = service.SelectAll(); // データ全件取得
		model.addAttribute("list", books); 
		return "show";
	}
	
	/* 登録画面の表示 */
	@GetMapping("/add")
	public String ShowInsert(BooksForm booksForm) {
		booksForm.setNewBooks(true); 
		return "add";
	}
		
    /* 登録機能 */
	@PostMapping("/insert")
	public String insert(@Validated BooksForm booksForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		/* FormからEntityに詰め替え */
		Books books = new Books();
		books.setIsbn_code(booksForm.getIsbn_code());
		books.setBook_title(booksForm.getBook_title());
		books.setAuthor(booksForm.getAuthor());
		books.setPublication_year(booksForm.getPublication_year());
		books.setPublisher(booksForm.getPublisher());
		
		/* 入力チェック */
		if( !bindingResult.hasErrors()) {
			service.InsertBooks(books);
			return "redirect:/books";
		} else {
			/* エラーがある場合は登録画面にエラーを表示 */
			booksForm.setNewBooks(true);
			return "add";
		}
	}
	
    /* 更新画面の表示 */
	@GetMapping("/{isbn_code}")
	public String ShowUpdate(BooksForm booksForm, @PathVariable String isbn_code, Model model) {
		/* 表示する行を取得 */
		Optional<Books> booksOpt = service.SelectOneById(isbn_code);
		/* booksFormに入れ直す */
		Optional<BooksForm> booksFormOpt = booksOpt.map(t -> makeBooksForm(t));
		/* booksFormがnullでなければ中身を取り出す */
		if(booksFormOpt.isPresent()) {
			booksForm = booksFormOpt.get();
		}
		makeUpdateModel(booksForm, model);
		return "add";
	}
	
	/* 更新用のModelを作成 */
	private void makeUpdateModel(BooksForm booksForm, Model model) {
		model.addAttribute("isbn_code", booksForm.getIsbn_code());
		booksForm.setNewBooks(false);
		model.addAttribute("booksForm", booksForm);
	}
	
    /* ISBNコードをキーで更新 */
	@PostMapping("/update")
	public String update(@Validated BooksForm booksForm, BindingResult result,
						 Model model, RedirectAttributes redirectAttributes) {
		Books books = new Books();
		books.setIsbn_code(booksForm.getIsbn_code());
		books.setBook_title(booksForm.getBook_title());
		books.setAuthor(booksForm.getAuthor());
		books.setPublication_year(booksForm.getPublication_year());
		books.setPublisher(booksForm.getPublisher());
		if(!result.hasErrors()) {
			service.UpdateBooks(books);
    		return "redirect:/books";
		} else {
			makeUpdateModel(booksForm, model);
			/* エラーがある場合は更新画面にエラーを表示させる */
			return "add";
        }
	}
	
	/* ISBNコードをキーで削除 */
	@PostMapping("/delete")
	public String delete(@RequestParam("isbn_code") String isbn_code, Model model){
		service.DeletebooksById(isbn_code);
		return "redirect:/books";
	}
	
	/**
	 * makeBooksFormはBooks(Entityクラス(DB関連クラス))からBooksForm(Formクラス)へ
	 * 値を代入しformを返している。
	 */
	private BooksForm makeBooksForm(Books books) {
		BooksForm form = new BooksForm();
		form.setIsbn_code(books.getIsbn_code());
		form.setBook_title(books.getBook_title());
		form.setAuthor(books.getAuthor());
		form.setPublication_year(books.getPublication_year());
		form.setPublisher(books.getPublisher());
		form.setNewBooks(false);
		return form;
	}
}