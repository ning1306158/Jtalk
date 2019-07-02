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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.demo.bean.Conversation;
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
	List<Conversation> conver = new LinkedList<Conversation>();

	public void index() {
		render("A.html");
	}

	public void login() {
		try {
			String name = getPara("name");
			if (name == null)
				render("login.html");
			else {
				setCookie("name", URLEncoder.encode(name, "utf-8"), -1);
				render("talk.html");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void toLogin() {
		render("login.html");
	}

	public void talk() {
		render("talk.html");
	}

	public void getAll() {
		if (conver.size() > 0) {
			Conversation c = conver.get(0);
			renderJson(c);
		} else {
			renderJson();
		}

	}

	public void conversation() {
		try {
			String s = get("content");
			String name = getCookie("name");
			Conversation conversation = new Conversation();
			conversation.setContent(s);
			if (name != null) {
				conversation.setName(URLDecoder.decode(name, "utf-8"));
			} else {
				name = "未命名" + (int) (Math.random() * 1000);
				setCookie("name", URLEncoder.encode(name, "utf-8"), -1);
				conversation.setName(name);
			}
			if (s != null && !s.equals("")) {
				renderJson(conversation);
				conver.add(conversation);
			} else {
				renderJson();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void accept() {
		String string = get("A");
		System.out.println(string);
		render("A.html");
	}

}
