package io.reactivesw.product.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.reactivesw.product.application.admin.model.actions.AddExternalImage;
import io.reactivesw.product.application.admin.model.actions.AddPrice;
import io.reactivesw.product.application.admin.model.actions.AddToCategory;
import io.reactivesw.product.application.admin.model.actions.AddVariant;
import io.reactivesw.product.application.admin.model.actions.ChangeMasterVariant;
import io.reactivesw.product.application.admin.model.actions.ChangePrice;
import io.reactivesw.product.application.admin.model.actions.Publish;
import io.reactivesw.product.application.admin.model.actions.RemoveFromCategory;
import io.reactivesw.product.application.admin.model.actions.RemoveImage;
import io.reactivesw.product.application.admin.model.actions.RemovePrice;
import io.reactivesw.product.application.admin.model.actions.RemoveVariant;
import io.reactivesw.product.application.admin.model.actions.RevertStagedChanges;
import io.reactivesw.product.application.admin.model.actions.SetAttribute;
import io.reactivesw.product.application.admin.model.actions.SetAttributeInAllVariants;
import io.reactivesw.product.application.admin.model.actions.SetCategoryOrderHint;
import io.reactivesw.product.application.admin.model.actions.SetDescription;
import io.reactivesw.product.application.admin.model.actions.SetKey;
import io.reactivesw.product.application.admin.model.actions.SetMetaDescription;
import io.reactivesw.product.application.admin.model.actions.SetMetaKeywords;
import io.reactivesw.product.application.admin.model.actions.SetMetaTitle;
import io.reactivesw.product.application.admin.model.actions.SetName;
import io.reactivesw.product.application.admin.model.actions.SetPrices;
import io.reactivesw.product.application.admin.model.actions.SetSearchKeywords;
import io.reactivesw.product.application.admin.model.actions.SetSku;
import io.reactivesw.product.application.admin.model.actions.SetSlug;
import io.reactivesw.product.application.admin.model.actions.SetVariantKey;
import io.reactivesw.product.application.admin.model.actions.Unpublish;

/**
 * Configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "action")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SetKey.class, name = "setKey"),
    @JsonSubTypes.Type(value = SetName.class, name = "setName"),
    @JsonSubTypes.Type(value = SetDescription.class, name = "setDescription"),
    @JsonSubTypes.Type(value = SetSlug.class, name = "setSlug"),
    @JsonSubTypes.Type(value = AddVariant.class, name = "addVariant"),
    @JsonSubTypes.Type(value = RemoveVariant.class, name = "removeVariant"),
    @JsonSubTypes.Type(value = ChangeMasterVariant.class, name = "changeMasterVariant"),
    @JsonSubTypes.Type(value = AddPrice.class, name = "addPrice"),
    @JsonSubTypes.Type(value = SetPrices.class, name = "setPrices"),
    @JsonSubTypes.Type(value = ChangePrice.class, name = "changePrice"),
    @JsonSubTypes.Type(value = RemovePrice.class, name = "removePrice"),
    @JsonSubTypes.Type(value = SetAttribute.class, name = "setAttribute"),
    @JsonSubTypes.Type(value = SetAttributeInAllVariants.class, name = "setAttributeInAllVariants"),
    @JsonSubTypes.Type(value = AddToCategory.class, name = "addToCategory"),
    @JsonSubTypes.Type(value = SetCategoryOrderHint.class, name = "setCategoryOrderHint"),
    @JsonSubTypes.Type(value = RemoveFromCategory.class, name = "removeFromCategory"),
    @JsonSubTypes.Type(value = SetSku.class, name = "setSku"),
    @JsonSubTypes.Type(value = SetVariantKey.class, name = "setProductVariantKey"),
    @JsonSubTypes.Type(value = AddExternalImage.class, name = "addExternalImage"),
    @JsonSubTypes.Type(value = RemoveImage.class, name = "removeImage"),
    @JsonSubTypes.Type(value = SetSearchKeywords.class, name = "setSearchKeywords"),
    @JsonSubTypes.Type(value = SetMetaTitle.class, name = "setMetaTitle"),
    @JsonSubTypes.Type(value = SetMetaDescription.class, name = "setMetaDescription"),
    @JsonSubTypes.Type(value = SetMetaKeywords.class, name = "setMetaKeywords"),
    @JsonSubTypes.Type(value = RevertStagedChanges.class, name = "revertStagedChanges"),
    @JsonSubTypes.Type(value = Publish.class, name = "publish"),
    @JsonSubTypes.Type(value = Unpublish.class, name = "unpublish")})
public interface UpdateAction {

  /**
   * Get action name.
   *
   * @return String
   */
  String getActionName();
}
