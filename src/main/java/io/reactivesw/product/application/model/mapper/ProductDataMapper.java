package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.product.application.model.ProductDataView;
import io.reactivesw.product.application.model.ProductDraft;
import io.reactivesw.product.application.model.SearchKeyword;
import io.reactivesw.product.domain.model.ProductData;
import io.reactivesw.product.domain.model.ProductVariant;
import io.reactivesw.product.infrastructure.util.ReferenceTypes;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/14.
 */
public class ProductDataMapper {

  private static int MASTER_VARIANT_ID = 1;

  public static ProductData modelToEntity(ProductDraft model) {
    ProductData entity = new ProductData();

    entity.setName(LocalizedStringMapper.modelToEntityDefaultNew(model.getName()));
    entity.setSlug(model.getSlug());
    entity.setDescription(LocalizedStringMapper.modelToEntityDefaultNew(model
        .getDescription()));
    entity.setMetaDescription(LocalizedStringMapper.modelToEntityDefaultNew(model
        .getMetaDescription()));
    entity.setMetaTitle(LocalizedStringMapper.modelToEntityDefaultNew(model.getMetaTitle
        ()));
    entity.setMetaKeywords(LocalizedStringMapper.modelToEntityDefaultNew(model
        .getMetaKeywords()));
    if (model.getSearchKeyword() != null) {
      entity.setSearchKeyWords(model.getSearchKeyword().getText());
    }
    ProductVariant masterVariant = new ProductVariant();
    if (model.getMasterVariant() != null) {
      masterVariant = ProductVariantMapper.modelToEntity(MASTER_VARIANT_ID, model
          .getMasterVariant());
    }
    masterVariant.setId(MASTER_VARIANT_ID);
    entity.setMasterVariant(masterVariant);

    if (model.getVariants() != null && !model.getVariants().isEmpty()) {
      entity.setVariants(ProductVariantMapper.modelToEntity(model.getVariants()));
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
      entity.setCategoryOrderHints(CategoryOrderHintsMapper.modelToEntity(
          model.getCategoryOrderHints()));
    }

    return entity;
  }

  public static ProductDataView entityToModel(ProductData entity) {
    ProductDataView model = new ProductDataView();

    model.setName(LocalizedStringMapper.entityToModelDefaultNull(entity.getName()));
    model.setSlug(entity.getSlug());
    model.setDescription(LocalizedStringMapper.entityToModelDefaultNull(entity.getDescription()));
    model.setMetaTitle(LocalizedStringMapper.entityToModelDefaultNull(entity.getMetaTitle()));
    model.setMetaDescription(LocalizedStringMapper.entityToModelDefaultNull(entity
        .getMetaDescription()));
    model.setMetaKeywords(LocalizedStringMapper.entityToModelDefaultNull(entity.getMetaKeywords()));
    if (StringUtils.isNotBlank(entity.getSearchKeyWords())) {
      model.setSearchKeyword(new SearchKeyword(entity.getSearchKeyWords(), null));
    }
    if (entity.getMasterVariant() != null) {
      model.setMasterVariant(ProductVariantMapper.entityToModel(entity.getMasterVariant()));
    }
    if (entity.getVariants() != null) {
      model.setVariants(ProductVariantMapper.entityToModel(entity.getVariants()));
    }
    if (entity.getCategories() != null) {
      model.setCategories(entity.getCategories().stream().map(
          category -> {
            return new Reference(ReferenceTypes.CATEGORY.getType(), category);
          }
      ).collect(Collectors.toList()));
    }
    if (entity.getCategoryOrderHints() != null) {
      model.setCategoryOrderHints(CategoryOrderHintsMapper.entityToModel(
          entity.getCategoryOrderHints()));
    }
    return model;
  }

}
