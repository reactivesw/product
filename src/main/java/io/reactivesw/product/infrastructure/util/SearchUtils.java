package io.reactivesw.product.infrastructure.util;

import com.google.common.collect.Lists;

import io.reactivesw.product.application.model.CategoryProductView;
import io.reactivesw.product.application.model.mapper.CategoryProductMapper;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.Attribute;
import io.reactivesw.product.domain.model.LocalizedStringValue;
import io.reactivesw.product.domain.model.Product;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The type Search utils.
 */
public final class SearchUtils {

  /**
   * Instantiates a new Search utils.
   */
  private SearchUtils() {
  }


  /**
   * Search product.
   *
   * @param searchWords the search words
   * @param products the products
   * @return the list
   */
  public static List<CategoryProductView> searchProduct(String searchWords,
      List<Product> products) {
    List<CategoryProductView> result = Lists.newArrayList();

    Consumer<Product> consumer = product -> result.addAll(matchProduct(searchWords, product));

    products.stream().forEach(consumer);

    Predicate<CategoryProductView> predicate = categoryProductView -> categoryProductView != null;
    return result.stream().filter(predicate).collect(Collectors.toList());
  }

  /**
   * Match product.
   *
   * @param searchWords the search words
   * @param product the product
   * @return the list
   */
  public static List<CategoryProductView> matchProduct(String searchWords, Product product) {
    List<CategoryProductView> result = Lists.newArrayList();

    if (product.getMasterData().getCurrent() != null) {
      result.add(matchMasterVariant(searchWords, product));
      result.addAll(matchVariants(searchWords, product));
    }

    return result;
  }

  /**
   * Fuzzy match variants.
   *
   * @param searchWords the search words
   * @param product the Product entity
   * @return list of CategoryProductView if match
   */
  private static List<CategoryProductView> matchVariants(String searchWords, Product product) {

    List<CategoryProductView> result = Lists.newArrayList();
    Consumer<ProductVariant> consumer = variant -> {
      if (FuzzyMatchUtils.isFuzzyMatch(searchWords, getCompareWordsForVariant(variant))) {
        result.add(
            CategoryProductMapper.toModel(product, variant)
        );
      }
    };

    if (product.getMasterData().getCurrent().getVariants() != null) {
      product.getMasterData().getCurrent().getVariants().stream().forEach(consumer);
    }

    return result;
  }

  /**
   * Fuzzy match masterVariant.
   *
   * @param searchWords the search words
   * @param product the Product entity
   * @return CategoryProductView if match
   */
  private static CategoryProductView matchMasterVariant(String searchWords, Product product) {
    CategoryProductView result = null;
    List<String> compareWords = getCompareWordsForMasterVariant(product);

    if (FuzzyMatchUtils.isFuzzyMatch(searchWords, compareWords)) {
      result = CategoryProductMapper.toModel(product,
          product.getMasterData().getCurrent().getMasterVariant());
    }

    return result;
  }

  /**
   * Get masterVariant compared words.
   *
   * @param product the Product
   * @return list of String
   */
  private static List<String> getCompareWordsForMasterVariant(Product product) {
    List<String> result = Lists.newArrayList();

    ProductData current = product.getMasterData().getCurrent();
    Set<LocalizedStringValue> name = current.getName();
    Set<LocalizedStringValue> description = current.getDescription();
    String searchKeyword = current.getSearchKeyWords();

    result.addAll(LocalizedStringMapper.getAllText(name));
    if (description != null) {
      result.addAll(LocalizedStringMapper.getAllText(description));
    }
    if (StringUtils.isNotBlank(searchKeyword)) {
      result.add(searchKeyword);
    }

    result.addAll(getCompareWordsForVariant(current.getMasterVariant()));

    return result;
  }

  /**
   * Get variant compared words.
   *
   * @param variant the ProductVariant
   * @return list of String
   */
  private static List<String> getCompareWordsForVariant(ProductVariant variant) {
    List<String> result = Lists.newArrayList();

    Consumer<Attribute> consumer = attribute -> result.add(attribute.getValue().toString());
    if (variant.getAttributes() != null) {
      variant.getAttributes().stream().forEach(consumer);
    }

    String sku = variant.getSku();
    if (StringUtils.isNotBlank(sku)) {
      result.add(sku);
    }

    return result;
  }
}
