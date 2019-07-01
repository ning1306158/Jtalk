package com.demo.blog;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * BlogInterceptor 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class BlogInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		if (!inv.getActionKey().endsWith("login")) {
			Controller controller = inv.getController();
			if (controller.getSession().getId().equals(controller.getCookie("JSESSIONID"))
					&& !controller.getCookie("name").trim().equals("")) {
				inv.invoke();
			} else {
				controller.render("login.html");
			}
		} else {
			inv.invoke();
		}
	}
}