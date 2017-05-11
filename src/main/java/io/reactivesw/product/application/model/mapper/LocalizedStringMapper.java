package io.reactivesw.product.application.model.mapper;

import com.google.common.collect.Sets;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.product.domain.model.LocalizedStringValue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * LocalizedString Mapper class.
 * Convert LocalizedString to LocalizedStringValue,
 * or Convert LocalizedStringValue to LocalizedString.
 */
public final class LocalizedStringMapper {

  /**
   * Instantiates a new Localized string mapper.
   */
  private LocalizedStringMapper() {
  }

  /**
   * Copy from set of LocalizedStringValue.
   *
   * @param entities the entities
   * @return the set
   */
  public static Set<LocalizedStringValue> copyFrom(Set<LocalizedStringValue> entities) {
    Set<LocalizedStringValue> result = Sets.newHashSet();

    Consumer<LocalizedStringValue> consumer = value ->
        result.add(LocalizedStringValue.build(value.getLanguage(), value.getText()));

    if (entities != null) {
      entities.stream().forEach(consumer);
    }

    return result;

  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return new LocalizedString.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString toModelDefaultNew(Set<LocalizedStringValue>
      localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    if (localizedStringEntities != null) {
      localizedString = toModel(localizedStringEntities);
    }
    return localizedString;
  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return null.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString toModelDefaultNull(Set<LocalizedStringValue>
      localizedStringEntities) {
    LocalizedString localizedString = null;
    if (localizedStringEntities != null) {
      localizedString = toModel(localizedStringEntities);
    }
    return localizedString;
  }


  /**
   * Convert to Set build LocalizedStringEntity from LocalizedString.
   *
   * @param localizedString the LocalizedString
   * @return Set build LocalizedStringEntity
   */
  public static Set<LocalizedStringValue> toEntityDefaultNew(LocalizedString
      localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = new HashSet<>();

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      localizedStringEntities = toEntity(localizedString, localizedStringEntities);
    }

    return localizedStringEntities;
  }


  /**
   * Model to entity default null set.
   *
   * @param localizedString the localized string
   * @return the set
   */
  public static Set<LocalizedStringValue> toEntityDefaultNull(LocalizedString
      localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = null;

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      localizedStringEntities = new HashSet<>();
      toEntity(localizedString, localizedStringEntities);
    }

    return localizedStringEntities;
  }

  /**
   * Convert to localized string.
   *
   * @param localizedStringEntities localizedStringEntities
   * @return LocalizedString
   */
  private static LocalizedString toModel(Set<LocalizedStringValue>
      localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    for (LocalizedStringValue localizedStringEntity : localizedStringEntities) {
      localizedString.addKeyValue(localizedStringEntity.getLanguage(), localizedStringEntity
          .getText());
    }
    return localizedString;
  }

  /**
   * build LocalizedStringValue from Map.Entry.
   *
   * @param localizedValue Map.Entry
   * @return LocalizedStringValue
   */
  private static LocalizedStringValue build(Map.Entry<String, String> localizedValue) {
    return LocalizedStringValue.build(localizedValue.getKey().toString(),
        localizedValue.getValue().toString());
  }

  /**
   * convert LocalizedString  to Set of LocalizedStringValue.
   *
   * @param localizedString LocalizedString
   * @param entities Set of LocalizedStringValue
   * @return Set of LocalizedStringValue
   */
  private static Set<LocalizedStringValue> toEntity(LocalizedString localizedString,
      Set<LocalizedStringValue> entities) {
    Set<Map.Entry<String, String>> localizedStrings = localizedString.getLocalized().entrySet();
    for (Map.Entry localizedValue : localizedStrings) {
      entities.add(build(localizedValue));
    }

    return entities;
  }
}
