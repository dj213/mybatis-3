package com.demo.plugins;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

@Intercepts({@Signature(
		type= StatementHandler.class,
		method = "query",
		args = {Statement.class,ResultHandler.class}),
		@Signature(
				type= StatementHandler.class,
				method = "prepare",
				args = {Connection.class,Integer.class}),
		@Signature(
				type= StatementHandler.class,
				method = "parameterize",
				args = {Statement.class}),
})
public class StatementHandlerPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println(this.getClass() + ":" + invocation.getMethod().getName());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
