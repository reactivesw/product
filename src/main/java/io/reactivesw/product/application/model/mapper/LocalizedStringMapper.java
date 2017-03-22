package io.reactivesw.product.application.model.mapper;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.domain.model.LocalizedStringValue;

import java.util.Set;

/**
 * Created by Davis on 16/11/30.
 */
public final class LocalizedStringMapper {

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return new LocalizedString.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString mapToModelDefaultNew(Set<LocalizedStringValue>
                                                                       localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    if (localizedStringEntities != null) {
      localizedString = mapToModel(localizedStringEntities);
    }
    return localizedString;
  }

  /**
   * Convert to localized string.
   *
   * @param localizedStringEntities localizedStringEntities
   * @return LocalizedString
   */
  private static LocalizedString mapToModel(Set<LocalizedStringValue>
                                                              localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    for (LocalizedStringValue localizedStringEntity : localizedStringEntities) {
      localizedString.addKeyValue(localizedStringEntity.getLanguage(), localizedStringEntity
          .getText());
    }
    return localizedString;
  }

}
