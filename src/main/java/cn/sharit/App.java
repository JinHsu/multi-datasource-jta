package cn.sharit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sharit.entity.Book;
import cn.sharit.repository1.BookDao1;
import cn.sharit.repository2.BookDao2;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@RestController
	public static class AppRestController {

		@Autowired
		BookDao1 repository1;

		@Autowired
		BookDao2 repository2;

		/**
		 * <h3>JTA事物管理未开启</h3>
		 * <p>
		 * repository1保存失败，repository2保存成功说明@Transactional默认用的是repository1的事务管理器
		 * </p>
		 * <h3>JTA事物开启</h3>
		 * <p>
		 * 要么都成功，要么都不成功
		 * </p>
		 */
		@Transactional
		@GetMapping("/test")
		public void test() {
			Book book = new Book("水浒传", "施耐庵");
			repository1.saveAndFlush(book);
			repository2.saveAndFlush(book);

			System.out.println(10 / 0);

		}

	}

}
