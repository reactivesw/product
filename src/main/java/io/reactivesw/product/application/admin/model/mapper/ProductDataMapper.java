package io.reactivesw.product.application.admin.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.admin.model.ProductDraft;
import io.reactivesw.product.application.model.ProductDataView;
import io.reactivesw.product.application.model.SearchKeyword;
import io.reactivesw.product.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

/**
 * ProductData Mapper class.
 * Convert ProductDraft to ProductData Entity,
 * or Convert ProductData Entity to ProductDataView.
 */
public final class ProductDataMapper {
  /**
   * master variant id.
   */
  private static final int MASTER_VARIANT_ID = 1;

  /**
   * Instantiates a new Product data mapper.
   */
  private ProductDataMapper() {
  }

  /**
   * Convert ProductDraft to ProductData Entity.
   *
   * @param model the model
   * @return the product data
   */
  public static ProductData toEntity(ProductDraft model) {
    ProductData entity = new ProductData();

    entity.setName(LocalizedStringMapper.toEntityDefaultNew(model.getName()));
    entity.setSlug(model.getSlug());
    entity.setDescription(LocalizedStringMapper.toEntityDefaultNew(model
        .getDescription()));
    entity.setMetaDescription(LocalizedStringMapper.toEntityDefaultNew(model
        .getMetaDescription()));
    entity.setMetaTitle(LocalizedStringMapper.toEntityDefaultNew(model.getMetaTitle()));
    entity.setMetaKeywords(LocalizedStringMapper.toEntityDefaultNew(model
        .getMetaKeywords()));
    if (model.getSearchKeyword() != null) {
      entity.setSearchKeyWords(model.getSearchKeyword().getText());
    }
    ProductVariant masterVariant = new ProductVariant();
    if (model.getMasterVariant() != null) {
      masterVariant = ProductVariantMapper.toEntity(MASTER_VARIANT_ID, model
          .getMasterVariant());
    }
    masterVariant.setId(MASTER_VARIANT_ID);
    entity.setMasterVariant(masterVariant);

    if (model.getVariants() != null && !model.getVariants().isEmpty()) {
      entity.setVariants(ProductVariantMapper.toEntity(model.getVariants()));
    }

    if (model.getCategories() != null && !model.getCategories().isEmpty()) {
      entity.setCategories(
          model.getCategories().stream().map(
              category -> {
                return category.getId();
              }
          ).collect(Collectors.toSet())
      );
    }

    if (model.getCategoryOrderHints() != null && !model.getCategoryOrderHints().isEmpty()) {
      entity.setCategoryOrderHints(CategoryOrderHintsMapper.toEntity(
          model.getCategoryOrderHints()));
    }

    return entity;
  }

  /**
   * Convert ProductData Entity to ProductDataView.
   *
   * @param entity the entity
   * @return the product data view
   */
  public static ProductDataView toModel(ProductData entity) {
    ProductDataView model = new ProductDataView();

    model.setName(LocalizedStringMapper.toModelDefaultNull(entity.getName()));
    model.setSlug(entity.getSlug());
    model.setDescription(LocalizedStringMapper.toModelDefaultNull(entity.getDescription()));
    model.setMetaTitle(LocalizedStringMapper.toModelDefaultNull(entity.getMetaTitle()));
    model.setMetaDescription(LocalizedStringMapper.toModelDefaultNull(entity
        .getMetaDescription()));
    model.setMetaKeywords(LocalizedStringMapper.toModelDefaultNull(entity.getMetaKeywords()));
    if (StringUtils.isNotBlank(entity.getSearchKeyWords())) {
      model.setSearchKeyword(new SearchKeyword(entity.getSearchKeyWords(), null));
    }
    if (entity.getMasterVariant() != null) {
      model.setMasterVariant(ProductVariantMapper.toModel(entity.getMasterVariant()));
    }
    if (entity.getVariants() != null) {
      model.setVariants(ProductVariantMapper.toModel(entity.getVariants()));
    }
    if (entity.getCategories() != null) {
      model.setCategories(entity.getCategories().stream().map(
          category -> {
            return new Reference(ReferenceTypes.CATEGORY.getType(), category);
          }).collect(Collectors.toList()));
    }
    if (entity.getCategoryOrderHints() != null) {
      model.setCategoryOrderHints(CategoryOrderHintsMapper.toModel(
          entity.getCategoryOrderHints()));
    }
    return model;
  }
}
