package com.ds.iwish.data.dao.generic;

/**
 * Provides common functionality in generic context for other DAOs to extend.
 *
 * @author DS
 */
public class GenericDAO {

    public static final String LIKE_PREFIX = "%";

    protected int getIndexFrom(int pPageSize, int pPageNum){
        return pPageSize * (pPageNum - 1);
    }

    protected String getStringForSqlLikeClause(String pStringToFormat){
        return LIKE_PREFIX + pStringToFormat + LIKE_PREFIX;
    }
}
