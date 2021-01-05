package cn.sharit.repository2;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sharit.entity.Book;

public interface BookDao2 extends JpaRepository<Book, String> {

}