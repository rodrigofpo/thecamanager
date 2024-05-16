package com.demo.thecamanager.services;

import com.demo.thecamanager.config.excepitionHandler.excepition.BusinessException;
import com.demo.thecamanager.config.excepitionHandler.excepition.ConflictException;
import com.demo.thecamanager.dto.BookDTO;
import com.demo.thecamanager.dto.LoanDTO;
import com.demo.thecamanager.dto.UserDTO;
import com.demo.thecamanager.entities.Book;
import com.demo.thecamanager.entities.Loan;
import com.demo.thecamanager.entities.User;
import com.demo.thecamanager.repositories.BookRepository;
import com.demo.thecamanager.repositories.LoanRepository;
import com.demo.thecamanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

        @Autowired
        UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    public Book createBook(BookDTO book) {
        try {
            boolean alreadyExists = bookRepository.existsByTitleContainsIgnoreCase(book.title());

            if (alreadyExists) throw new ConflictException("O livro ja esta cadastrado.");

            User userOwner = (User) userRepository.findByCpf(book.user().cpf());
            Book newBook = new Book();
            newBook.setTitle(book.title());
            newBook.setAuthor(book.author());
            newBook.setUser(userOwner);
            return bookRepository.save(newBook);
        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar livro!");
        }
    }

    public Book updateBook(BookDTO book) {
        try {
            Book upBook = bookRepository.findById(book.id())
                    .orElseThrow(() -> new NullPointerException("Livro nao encontrado!"));

            UserDetails bookUser = userRepository.findByCpf(book.user().cpf());

            if (bookUser.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                upBook.setTitle(book.title());
                upBook.setAuthor(book.author());
            }

            if (bookUser.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {
                if (book.user().cpf().equals(bookUser.getUsername())) {
                    upBook.setTitle(book.title());
                    upBook.setAuthor(book.author());
                }
            }

            return bookRepository.save(upBook);

        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar livro!");
        }
    }

    public Loan requestABookLoan(LoanDTO loan) {
        try {
            User user = (User) userRepository.findByCpf(loan.user().cpf());
            Book book = bookRepository
                    .findById(UUID.fromString(String.valueOf(loan.book().id())))
                    .orElseThrow(() -> new NullPointerException("Livro nao encontrado!"));

            Loan newloan = new Loan();
            newloan.setBook(book);
            newloan.setUser(user);
            newloan.setReturnDate(loan.returnDate());
            newloan.setLoanDate(loan.loanDate());

            return loanRepository.save(newloan);

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar livro!");
        }
    }

    public Loan updateRequestABookLoan(LoanDTO loan) {
        try {
            Loan uploan = loanRepository.findById(loan.id())
                    .orElseThrow(() -> new NullPointerException("!"));
            uploan.setReturnDate(loan.returnDate());
            uploan.setStatus(loan.status());

            return loanRepository.save(uploan);

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar livro!");
        }
    }

    public Book getOneBook(BookDTO book) {
        try {
            UserDetails bookUser = userRepository.findByCpf(book.user().cpf());

            if (bookUser.getAuthorities().stream().noneMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                throw new BusinessException("Nao autorizado!");
            }

            return bookRepository
                    .findById(book.id())
                    .orElseThrow(() -> new NullPointerException("Livro nao encontrado!"));
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar livro!");
        }
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar livros!");
        }
    }

    public String deleteBook(String id, UserDTO user) {
        try {
            UserDetails userDetails = userRepository.findByCpf(user.cpf());
            if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                Optional<Book> book = bookRepository.findById(UUID.fromString(id));
                book.ifPresent(bookRepository::delete);
            }

            if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {
                Optional<Book> book = bookRepository.findByIdAndUser_Cpf(UUID.fromString(id), user.cpf());
                book.ifPresent(bookRepository::delete);
            }

            return "Livro deletado com sucesso!";
        } catch (Exception e) {
            throw new BusinessException("Erro ao deletar livro!");
        }
    }
}
