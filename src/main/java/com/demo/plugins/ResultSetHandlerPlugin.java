package com.demo.plugins;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(
		type= ResultSetHandler.class,
		method = "handleResultSets",
		args = {Statement.class}),
		@Signature(
				type= ResultSetHandler.class,
				method = "handleOutputParameters",
				args = {CallableStatement.class})})
public class ResultSetHandlerPlugin implements Interceptor {

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
