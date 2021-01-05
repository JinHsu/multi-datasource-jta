package cn.sharit.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sharit.entity.Book;

public interface BookDao1 extends JpaRepository<Book, String> {

}