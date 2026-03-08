package com.college.erp.service;

import com.college.erp.model.Book;
import com.college.erp.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public void save(Book book) { repo.save(book); }

    public List<Book> getAll() { return repo.findAll(); }

    public Book getById(Long id) { return repo.findById(id).get(); }

    public void delete(Long id) { repo.deleteById(id); }

    public List<Book> getAvailable() { return repo.findAvailableBooks(); }

    public List<Book> search(String keyword) { return repo.searchBooks(keyword); }

    public long countAvailable() { return repo.findAvailableBooks().size(); }

    public long countByDept(String dept) { return repo.findByDepartment(dept).size(); }
}