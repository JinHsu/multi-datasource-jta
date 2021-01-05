package cn.sharit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.sharit.entity.Book;
import cn.sharit.repository1.BookDao1;
import cn.sharit.repository2.BookDao2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

	@Autowired
	BookDao1 repository1;

	@Autowired
	BookDao2 repository2;

	@Test
	@Transactional
	@Rollback(false)
	public void save() {
		Book book = new Book("水浒传", "施耐庵");
		repository1.saveAndFlush(book);
		repository2.saveAndFlush(book);

		int a = 10;
		int b = a / 0;
		System.out.println(b);
	}

}