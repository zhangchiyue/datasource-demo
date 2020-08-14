package com.hitman_zhang.datasource;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.hitman_zhang.datasource.common.datasource.annotation.TransactionSource;
import com.hitman_zhang.datasource.modules.book.BookMapper;
import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import com.hitman_zhang.datasource.modules.book.service.BookService;
import com.hitman_zhang.datasource.modules.game.mapper.GameMapper;
import com.hitman_zhang.datasource.modules.game.pojo.GamePojo;
import com.hitman_zhang.datasource.modules.game.service.GameService;
import com.hitman_zhang.datasource.modules.user.mapper.UserMapper;
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

    @Autowired
    GameService gameService;


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

    @Test
    public void test3(){
        GamePojo game= GamePojo.builder().name("LOL").type("MOBA").build();
        gameService.addGame(game);
    }



    @TransactionSource
    @Test
    public void test4(){
        UserPojo user = UserPojo.builder().age(20).name("manson").build();
        userService.insert(user);
        BookPojo book = BookPojo.builder().name("剑来").code(10001).build();
        bookService.insert(book);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1/0;
        GamePojo game= GamePojo.builder().name("炉石").type("card").build();
        gameService.addGame(game);
    }




    @Test
    public void test5(){
        UserPojo user = UserPojo.builder().age(20).name("manson").build();
        BookPojo book = BookPojo.builder().name("剑来").code(10001).build();
        GamePojo game= GamePojo.builder().name("炉石").type("card").build();
        int i = userService.insertAll(user, book, game);
        System.out.println(i);
    }


}
