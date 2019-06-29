package com.demo.blog;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.demo.common.model.Blog;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {

	@Inject
	BlogService service;
	List<String> conver=new ArrayList<String>();
	public void index() {
		render("A.html");
	}

	public void add() {
		System.out.println("A");
	}
	public void talk() {
		render("talk.html");
	}
	public void conversation() {
		String s=get("content");
//		conver.add(s);
		System.out.println(s);
		renderJson("AAA");
	}

	public void accept() {
		String string = get("A");
		System.out.println(string);
		render("A.html");
	}

}
