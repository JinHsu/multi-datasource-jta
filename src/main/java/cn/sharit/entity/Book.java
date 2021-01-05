package cn.sharit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//生成数据库表的关键注解，后面的name是表名
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BOOK")
public class Book implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2") /* uuid strategy is deprecated, using uuid2 instead */
	private String id;

	@Column
	private String bookname;

	@Column
	private String author;

	public Book() {

	}

	public Book(String bookname, String author) {
		this.bookname = bookname;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", bookname='" + bookname + '\'' + ", author='" + author + '\'' + '}';
	}

}
