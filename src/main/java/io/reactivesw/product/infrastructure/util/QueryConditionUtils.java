package io.reactivesw.product.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import io.reactivesw.database.dialect.JSONBUserType;
import io.reactivesw.product.application.model.QueryConditions;

import java.io.IOException;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Created by Davis on 16/12/22.
 */
public final class QueryConditionUtils {

  /**
   * Instantiates a new Query condition update.
   */
  private QueryConditionUtils() {}

  /**
   * Gets category id.
   *
   * @param queryConditions the query conditions
   * @return the category id
   * TODO only for categoryId, example: categoryId:"2222201-1111111"
   */
  public static String getCategoryId(QueryConditions queryConditions) {
    String where = queryConditions.getWhere();
    String[] conditions = where.split(":");
    return conditions[conditions.length - 1].replaceAll("\"", "");
  }
}
