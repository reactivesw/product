package io.reactivesw.product.application.model.attribute;

import com.fasterxml.jackson.annotation.JsonCreator;

//TOOD copy from sdk, should be TextType
public final class StringAttributeType extends AttributeTypeBase {
//    @JsonCreator
    private StringAttributeType() {}

    @JsonCreator
    public static StringAttributeType of() {
        return new StringAttributeType();
    }
}
