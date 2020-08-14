package com.hitman_zhang.datasource.modules.book.service.impl;

import com.hitman_zhang.datasource.modules.book.BookMapper;
import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import com.hitman_zhang.datasource.modules.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public int insert(BookPojo book) {
        return bookMapper.insert(book);
    }
}
