package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

public class DruidSqlParserTest {

	@Test
	public void testExprParser() {

		SQLExprParser exprParser = new SQLExprParser("AGE > 5");
		SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) exprParser.expr();
		 
		Assert.assertEquals(SQLBinaryOperator.GreaterThan, binaryOpExpr.getOperator());
		
		SQLIdentifierExpr left = (SQLIdentifierExpr) binaryOpExpr.getLeft();
		SQLIntegerExpr right = (SQLIntegerExpr) binaryOpExpr.getRight();
		 
		Assert.assertEquals("AGE", left.getName());
		Assert.assertEquals(5, right.getNumber().intValue());
		
	}
	
	@Test
	public void testStatementParser() {
		SQLStatementParser statParser = SQLParserUtils.createSQLStatementParser("select tdate,count(*) cnt,count(distinct username) ucnt from st_user", "hive");
		SQLSelect select = statParser.parseSelect().getSelect();
		System.out.println(select.toString());
		select.getQuery().accept(new SQLASTVisitorAdapter(){
			@Override
			public boolean visit(SQLSelectItem x) {
				System.out.println("column:"+x);
				return super.visit(x);
			}
			public boolean visit(SQLExprTableSource x){
				System.out.println("table:"+x);
				return super.visit(x);
			}
		});
	}
	
}
