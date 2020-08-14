package com.hitman_zhang.datasource;

import com.hitman_zhang.datasource.common.datasource.annotation.TransactionSource;
import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import com.hitman_zhang.datasource.modules.book.service.BookService;
import com.hitman_zhang.datasource.modules.user.pojo.UserPojo;
import com.hitman_zhang.datasource.modules.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDatasource {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;


    @Test
    @TransactionSource
    public void test1(){
        UserPojo hitman47 = UserPojo.builder().age(18).name("hitman47").build();
        userService.insert(hitman47);
    }

    @Test
    public void test2(){
        BookPojo book = BookPojo.builder().name("百年孤独").code(10000).build();
        bookService.insert(book);
    }


}
