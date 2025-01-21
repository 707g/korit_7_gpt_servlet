package com.korit.servlet_study.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .bookId(1234)
                .bookName("test title")
                .authorId(1234)
                .publisherId(3333)
                .categoryId(4444)
                .bookImgUrl("http://test")
                .build();

        String jsonBook = objectMapper.writeValueAsString(book);
        System.out.println(jsonBook);

        response.setContentType("application/json");
        response.getWriter().println(jsonBook);
    }
}
