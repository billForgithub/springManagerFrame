//package com.github.zuihou.database.parsers;
//
//
//import java.util.List;
//
//import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
//import com.baomidou.mybatisplus.core.toolkit.Assert;
//import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//import net.sf.jsqlparser.expression.BinaryExpression;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.Parenthesis;
//import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
//import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
//import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
//import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
//import net.sf.jsqlparser.expression.operators.relational.InExpression;
//import net.sf.jsqlparser.expression.operators.relational.ItemsList;
//import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
//import net.sf.jsqlparser.expression.operators.relational.SupportsOldOracleJoinSyntax;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.schema.Table;
//import net.sf.jsqlparser.statement.delete.Delete;
//import net.sf.jsqlparser.statement.insert.Insert;
//import net.sf.jsqlparser.statement.select.FromItem;
//import net.sf.jsqlparser.statement.select.Join;
//import net.sf.jsqlparser.statement.select.LateralSubSelect;
//import net.sf.jsqlparser.statement.select.PlainSelect;
//import net.sf.jsqlparser.statement.select.SelectBody;
//import net.sf.jsqlparser.statement.select.SetOperationList;
//import net.sf.jsqlparser.statement.select.SubJoin;
//import net.sf.jsqlparser.statement.select.SubSelect;
//import net.sf.jsqlparser.statement.select.ValuesList;
//import net.sf.jsqlparser.statement.select.WithItem;
//import net.sf.jsqlparser.statement.update.Update;
//
///**
// * 租户 SQL 解析（ Schema 表级 ）
// *
// * @author zuihou
// * @since 2019-10-12
// */
//@Data
//@Accessors(chain = true)
//@EqualsAndHashCode(callSuper = true)
//public class TenantSchemaSqlParser extends AbstractJsqlParser {
//
//    private TenantSchemaHandler tenantSchemaHandler;
//
//    @Override
//    public void processInsert(Insert insert) {
//        if (tenantSchemaHandler.doTableFilter(insert.getTable().getName())) {
//            // 过滤退出执行
//            return;
//        }
////        insert.getColumns().add(new Column(tenantSchemaHandler.getTenantIdColumn()));
////        if (insert.getSelect() != null) {
////            processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
////        } else if (insert.getItemsList() != null) {
////            // fixed github pull/295
////            ItemsList itemsList = insert.getItemsList();
////            if (itemsList instanceof MultiExpressionList) {
////                ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(tenantHandler.getTenantId(false)));
////            } else {
////                ((ExpressionList) insert.getItemsList()).getExpressions().add(tenantSchemaHandler.getTenantId(false));
////            }
////        } else {
////            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
////        }
//    }
//
//    @Override
//    public void processDelete(Delete delete) {
//        if (tenantSchemaHandler.doTableFilter(delete.getTable().getName())) {
//            // 过滤退出执行
//            return;
//        }
//        delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere()));
//    }
//
//    @Override
//    public void processUpdate(Update update) {
//        List<Table> tableList = update.getTables();
//        Assert.isTrue(null != tableList && tableList.size() < 2,
//                "Failed to process multiple-table update, please exclude the statementId");
//        Table table = tableList.get(0);
//        if (tenantSchemaHandler.doTableFilter(table.getName())) {
//            // 过滤退出执行
//            return;
//        }
//        update.setWhere(this.andExpression(table, update.getWhere()));
//    }
//
//    @Override
//    public void processSelectBody(SelectBody selectBody) {
//        // 简单的查询
//        if (selectBody instanceof PlainSelect) {
//            processPlainSelect((PlainSelect) selectBody);
//        } else if (selectBody instanceof WithItem) {
//            WithItem withItem = (WithItem) selectBody;
//            if (withItem.getSelectBody() != null) {
//                processSelectBody(withItem.getSelectBody());
//            }
//        } else {
//            SetOperationList operationList = (SetOperationList) selectBody;
//            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
//                operationList.getSelects().forEach(this::processSelectBody);
//            }
//        }
//    }
//
//    /**
//     * delete update 语句 where 处理
//     */
//    protected BinaryExpression andExpression(Table table, Expression where) {
//        //获得where条件表达式
//        EqualsTo equalsTo = new EqualsTo();
////        equalsTo.setLeftExpression(this.getAliasColumn(table));
////        equalsTo.setRightExpression(tenantSchemaHandler.getTenantId(true));
//        if (null != where) {
//            if (where instanceof OrExpression) {
//                return new AndExpression(equalsTo, new Parenthesis(where));
//            } else {
//                return new AndExpression(equalsTo, where);
//            }
//        }
//        return equalsTo;
//    }
//
//    /**
//     * 处理 PlainSelect
//     */
//    protected void processPlainSelect(PlainSelect plainSelect) {
//        processPlainSelect(plainSelect, false);
//    }
//
//    /**
//     * 处理 PlainSelect
//     *
//     * @param plainSelect ignore
//     * @param addColumn   是否添加租户列,insert into select语句中需要
//     */
//    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
//        FromItem fromItem = plainSelect.getFromItem();
//        if (fromItem instanceof Table) {
//            Table fromTable = (Table) fromItem;
//            if (!this.getTenantSchemaHandler().doTableFilter(fromTable.getName())) {
//                //修改schema
//
//                String tenantSchema = this.getTenantSchemaHandler().getTenantSchema();
//                fromTable.setSchemaName(tenantSchema);
//            }
//        } else {
//            processFromItem(fromItem);
//        }
//        Expression where = plainSelect.getWhere();
//        List<Join> joins = plainSelect.getJoins();
//        if (joins != null && joins.size() > 0) {
//            joins.forEach(j -> {
//                processJoin(j);
//                processFromItem(j.getRightItem());
//            });
//        }
//
//
//
//
//
//
//    }
//
//    /**
//     * 处理子查询等
//     */
//    protected void processFromItem(FromItem fromItem) {
//        if (fromItem instanceof SubJoin) {
//            //子连接
//            SubJoin subJoin = (SubJoin) fromItem;
//            if (subJoin.getJoinList() != null) {
//                subJoin.getJoinList().forEach(this::processJoin);
//            }
//            if (subJoin.getLeft() != null) {
//                processFromItem(subJoin.getLeft());
//            }
//        } else if (fromItem instanceof SubSelect) {
//            //子查询
//            SubSelect subSelect = (SubSelect) fromItem;
//            if (subSelect.getSelectBody() != null) {
//                processSelectBody(subSelect.getSelectBody());
//            }
//        } else if (fromItem instanceof ValuesList) {
//            logger.debug("Perform a subquery, if you do not give us feedback");
//        } else if (fromItem instanceof LateralSubSelect) {
//            // 横向子查询？
//            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
//            if (lateralSubSelect.getSubSelect() != null) {
//                SubSelect subSelect = lateralSubSelect.getSubSelect();
//                if (subSelect.getSelectBody() != null) {
//                    processSelectBody(subSelect.getSelectBody());
//                }
//            }
//        }
//    }
//
//    /**
//     * 处理联接语句
//     */
//    protected void processJoin(Join join) {
//        if (join.getRightItem() instanceof Table) {
//            Table fromTable = (Table) join.getRightItem();
//            if (this.tenantSchemaHandler.doTableFilter(fromTable.getName())) {
//                // 过滤退出执行
//                return;
//            }
//            String tenantSchema = this.getTenantSchemaHandler().getTenantSchema();
//            fromTable.setSchemaName(tenantSchema);
//        }
//    }
//
//    /**
//     * 处理条件:
//     * 支持 getTenantHandler().getTenantId()是一个完整的表达式：tenant in (1,2)
//     * 默认tenantId的表达式： LongValue(1)这种依旧支持
//     */
//    protected Expression builderExpression(Expression currentExpression, Table table) {
//        final Expression tenantExpression = null;
////        final Expression tenantExpression = this.getTenantSchemaHandler().getTenantId(false);
//        Expression appendExpression;
//        if (!(tenantExpression instanceof SupportsOldOracleJoinSyntax)) {
//            appendExpression = new EqualsTo();
//            ((EqualsTo) appendExpression).setLeftExpression(this.getAliasColumn(table));
//            ((EqualsTo) appendExpression).setRightExpression(tenantExpression);
//        } else {
//            appendExpression = processTableAlias4CustomizedTenantIdExpression(tenantExpression, table);
//        }
//        if (currentExpression == null) {
//            return appendExpression;
//        }
//        if (currentExpression instanceof BinaryExpression) {
//            BinaryExpression binaryExpression = (BinaryExpression) currentExpression;
//            if (binaryExpression.getLeftExpression() instanceof FromItem) {
//                processFromItem((FromItem) binaryExpression.getLeftExpression());
//            }
//            if (binaryExpression.getRightExpression() instanceof FromItem) {
//                processFromItem((FromItem) binaryExpression.getRightExpression());
//            }
//        } else if (currentExpression instanceof InExpression) {
//            InExpression inExp = (InExpression) currentExpression;
//            ItemsList rightItems = inExp.getRightItemsList();
//            if (rightItems instanceof SubSelect) {
//                processSelectBody(((SubSelect) rightItems).getSelectBody());
//            }
//        }
//        if (currentExpression instanceof OrExpression) {
//            return new AndExpression(new Parenthesis(currentExpression), appendExpression);
//        } else {
//            return new AndExpression(currentExpression, appendExpression);
//        }
//    }
//
//    /**
//     * 目前: 针对自定义的tenantId的条件表达式[tenant_id in (1,2,3)]，无法处理多租户的字段加上表别名
//     * select a.id, b.name
//     * from a
//     * join b on b.aid = a.id and [b.]tenant_id in (1,2) --别名[b.]无法加上 TODO
//     *
//     * @param expression
//     * @param table
//     * @return 加上别名的多租户字段表达式
//     */
//    protected Expression processTableAlias4CustomizedTenantIdExpression(Expression expression, Table table) {
//        //cannot add table alias for customized tenantId expression,
//        // when tables including tenantId at the join table poistion
//        return expression;
//    }
//
//    /**
//     * 租户字段别名设置
//     * <p>tableName.tenantId 或 tableAlias.tenantId</p>
//     *
//     * @param table 表对象
//     * @return 字段
//     */
//    protected Column getAliasColumn(Table table) {
//        StringBuilder column = new StringBuilder();
//        if (null == table.getAlias()) {
//            column.append(table.getName());
//        } else {
//            column.append(table.getAlias().getName());
//        }
//        column.append(StringPool.DOT);
////        column.append(tenantHandler.getTenantIdColumn());
//        return new Column(column.toString());
//    }
//}
