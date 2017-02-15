package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class MoneyAttributeType extends AttributeTypeBase {
    private MoneyAttributeType() {}

    @JsonIgnore
    public static MoneyAttributeType of() {
        return new MoneyAttributeType();
    }
}
