package org.lch.attendance.util;
import java.beans.Introspector;
import java.util.List;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategyUtil;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.util.StringHelper;

public class Persistent extends DelegatingReverseEngineeringStrategy {

	public Persistent(ReverseEngineeringStrategy delegate) {
		super(delegate);
	}

	// public String columnToPropertyName(TableIdentifier table, String column)
	// {
	// return column.toLowerCase();
	// }
	public static void getL(int x, String... strings) {
		for (String s : strings) {
			System.out.println(s);
		}
	}

	/**
	 * collection问题
	 */
	public String foreignKeyToCollectionName(String keyname,
			TableIdentifier fromTable, List fromColumns,
			TableIdentifier referencedTable, List referencedColumns,
			boolean uniqueReference) {
		String propertyName = Introspector.decapitalize(StringHelper
				.unqualify(tableToClassName(fromTable)));
		propertyName = ReverseEngineeringStrategyUtil
				.simplePluralize(propertyName);
		return propertyName;
	}

	/***
	 * 外键问题
	 */
	public String foreignKeyToEntityName(String keyname,
			TableIdentifier fromTable, List fromColumnNames,
			TableIdentifier referencedTable, List referencedColumnNames,
			boolean uniqueReference) {

		// return super.foreignKeyToEntityName(keyname, fromTable,
		// fromColumnNames, referencedTable, referencedColumnNames,
		// uniqueReference);
		String propertyName = Introspector.decapitalize(StringHelper
				.unqualify(tableToClassName(referencedTable)));

		return propertyName;
	}

	public String columnToPropertyName(TableIdentifier table, String column) {

		String decapitalize = Introspector
				.decapitalize(ReverseEngineeringStrategyUtil
						.toUpperCamelCase(column));
		// 关键字处理
		if (ReverseEngineeringStrategyUtil.isReservedJavaKeyword(decapitalize)) {
			decapitalize = decapitalize + "_";
		}
		return decapitalize;
	}

	public String tableToClassName(TableIdentifier tableIdentifier) {
		if (tableIdentifier != null) {
			// 先获取包路径
			String prifix = super.tableToClassName(tableIdentifier);
			System.out.print("prifix:" + prifix);
			int lastpoint = prifix.lastIndexOf(".");
			String packagename = prifix.substring(0, lastpoint + 1);
			// JOptionPane.showMessageDialog(null,packagename);
			String tablename = tableIdentifier.getName();
			String[] tmp = tablename.split("_");
			// 最终的表名
			String finalClassName = "";
			String firstChar = "";
			String otherChar = "";
			if (tmp.length == 1) {
				// 没有下划线
				firstChar = tmp[0].substring(0, 1).toUpperCase();
				otherChar = tmp[0].substring(1).toLowerCase();
				finalClassName = firstChar + otherChar;
			} else {
				for (int i = 1; i < tmp.length; i++) {
					firstChar = tmp[i].substring(0, 1).toUpperCase();
					otherChar = tmp[i].substring(1).toLowerCase();
					finalClassName = finalClassName + firstChar + otherChar;
				}

				// int flag = tablename.indexOf("_");
				// String newObjName = tablename.substring(flag+1,
				// tablename.length());
				// finalClassName = finalClassName + newObjName;

			}
			return packagename + finalClassName;
		} else {

			return super.tableToClassName(tableIdentifier);
		}
	}

	public static void main(String[] args) {
		getL(1, "a", "b", "c");

		String var = "aa_bb_cc";
		int size = var.indexOf("_");
		String result = var.substring(size + 1, var.length());
		System.out.println(result);
	}

}