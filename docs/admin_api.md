# Product Service Admin Rest API

## 1. Introduction

TODO

## 2. View Model

### ProductView

| field name | field type | comments |
|-----|-----|-----|
| id | String | product id in system. |
| key | String | |
| version | Integer | |
| productType | Reference | |
| masterData | ProductCatalogDataView | |

### ProductCatalogDataView

| field name | field type | comments |
|-----|-----|-----|
| published | Boolean | |
| current | ProductDataView | |
| staged | ProductDataView | |
| hasStagedChanges | Boolean | |

### ProductDataView

| field name | field type | comments |
|-----|-----|-----|
| name | LocalizedString | |
| categories | List\<Reference\> | |
| categoryOrderHints | List\<CategoryOrderHintView\> | |
| description | LocalizedString | |
| slug | String | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | |
| metaKeywords | LocalizedString | |
| masterVariant | ProductVariantView | |
| variants | List\<ProductVariantView\> | |
| searchKeyword | SearchKeyword | |

### ProductVariantView

| field name | field type | comments |
|-----|-----|-----|
| id | Integer | |
| sku | String | |
| key | String | |
| prices | List\<PriceView\> | |
| attributes | List\<AttributeView\> | |
| price | PriceView | |
| images | List\<ImageView\> | |
| availability | ProductVariantAvailabilityView | |
| matchingVariant | Boolean | |

### PriceView

| field name | field type | comments |
|-----|-----|-----|
| id | String | |
| value | Money | |
| country | String | |
| customerGroup | Reference | |
| channel | Reference | |
| validFrom | ZonedDateTime | |
| validUntil | ZonedDateTime | |

### AttributeView

| field name | field type | comments |
|-----|-----|-----|
| name | String | |
| value | JsonNode | |

### ImageView

| field name | field type | comments |
|-----|-----|-----|
| url | String | |
| dimensions | AssetDimensionsView | |
| label | String | |

### AssetDimensionsView

| field name | field type | comments |
|-----|-----|-----|
| width | Float | |
| height | Float | |

### ProductVariantAvailabilityView

| field name | field type | comments |
|-----|-----|-----|
| isOnStock | Boolean | |
| restockableInDays | Integer | |
| availableQuantity | Integer | |

### ProductDraft

| field name | field type | comments |
|-----|-----|-----|
| key | String | |
| name | LocalizedString | required. |
| productType | Resource | required.|
| slug | String | required. Allowed are alphabetic, numeric, underscore (_) and hyphen (-) characters. Maximum size is 256. Minimum size is 2. | 
| description | LocalizedString | |
| categories | List\<Reference\> | |
| categoryOrderHints | List\<CategoryOrderHintView\> | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | |
| metaKeywords | LocalizedString | |
| masterVariant | ProductVariantDraft | required. |
| variants | List\<ProductVariantDraft\> | |
| searchKeyword | SearchKeyword | |
| published | Boolean | |

### ProductVariantDraft

| field name | field type | comments |
|-----|-----|-----|
| sku | String | required. Allowed are alphabetic, numeric, underscore (_) and hyphen (-) characters. Maximum size is 256. Minimum size is 2. |
| key | String | |
| prices | List\<PriceDraft\> | |
| images | List\<ImageView\> | |
| attributes | List\<AttributeView\> | |

### UpdateRequest


| field name | field type | comments |
|---|---|---|
| version | Integer | required, NotNull, min is 0 |
| actions | List\<UpdateAction\> | required, NotNull |

### Update Actions

#### Set Key

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setKey` |
| key | String | optional. User-specific unique identifier for the product. If left blank or set to `null`, the product key is unset/removed. |

#### Set Name

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setName` |
| name | LocalizedString | required |

#### Set Description

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setDescription` |
| description | LocalizedString | required |

#### Set Slug

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setSlug` |
| slug | String | required. Every slug must be unique across a project, but a product can have the same slug for different languages. Allowed are alphabetic, numeric, underscore (_) and hyphen (-) characters. Maximum size is 256. |

#### Add ProductVariant

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `addVariant` |
| sku | String | optional |
| key | String | optional |
| prices | List\<Price\> | optional |
| images | List\<ImageView\> | optional |
| attributes | List\<AttributeView\> | optional. The custom attributes of the master variant. Each attribute is a JSON object where a field name corresponds to the name of a product attribute defined on the referenced ProductType and the value being a valid JSON value for that attribute. |


#### Remove ProductVariant

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `removeVariant` |
| variantId | Integer | required. This is the variant id in this product. |

#### Change Master Variant

Sets the given variant as the new master variant. The previous master variant is added to the back of the list of variants.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `changeMasterVariant` |
| variantId | Integer | required. This is the variant id in this product. |

#### Add Price

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `addPrice` |
| variantId | Integer | required. This is the variant id in this product. |
| price | PriceDraft | required |

#### Set Prices

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setPrices` |
| variantId | Integer | required. This is the variant id in this product. |
| prices | List\<PriceDraft\> | required |

#### Change Price

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `changePrice` |
| variantId | Integer | required. This is the variant id in this product. |
| priceId | String | required, Id of the `Price` |
| price | PriceDraft | required |

#### Remove Price

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `removePrice` |
| variantId | Integer | required. This is the variant id in this product. |
| priceId | String | required, Id of the `Price` |

#### Set Attribute

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setAttribute` |
| variantId | Integer | required. This is the variant id in this product. |
| name | String | required |
| value | Json value | optional. If the attribute exists and the value is omitted or set to null, the attribute is removed. If the attribute exists and a value is provided, the new value is applied. If the attribute does not exist and a value is provided, it is added as a new attribute. |

The AttributeType determines the format for the value to be provided, in particular:

* for EnumType and LocalizableEnumType attributes:
  * either only the key of the EnumValue or of the LocalizedEnumValue is to be used as value
  * or the complete EnumValue or the complete LocalizedEnumValue is to be used as value
* for LocalizableTextType attributes the LocalizedString object is to be used as value
* for MoneyType attributes the Money object is to be used as value
* for SetType attributes the entire set object is to be used as value
* for NestedType attributes the list of values of all attributes of the nested product is to be used as value
* for ReferenceType attributes the Reference object is to be used as value


#### Set Attribute in All Variants

Adds / Removes / Changes a custom attribute in all variants at the same time. It can be helpful to set attribute values that are constrained with `SameForAll`.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setAttributeInAllVariants` |
| name | String |required |
| value | Json value | optional. The same update behavior as for `Set Attribute` applies. |

#### Add to Category

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `addToCategory` |
| category | Reference | required |

#### Set Category Order Hint

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setCategoryOrderHint` |
| categoryId | String | required. Id of a Category the product belongs to. |
| previousOrderHint | String | required, NotNull |
| nextOrderHint | String | if product is changed to be the last one, this parameter should be empty, otherwise, required |

#### Remove from Category

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `removeFromCategory` |
| category | Reference | required |

#### Set SKU

Adds, changes or removes a SKU on a product variant. A SKU can only be changed or removed from a variant through this operation if there is no inventory entry associated with that SKU.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setSku` |
| variantId | Integer | required. This is the variant id in this product. |
| sku | String | optional. If left blank or set to null, the sku is unset/removed. |

#### Set ProductVariant Key

Adds, changes or removes a key on a product variant.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setProductVariantKey` |
| variantId | Integer | required. This is the variant id in this product. |
| key | String | optional. If left blank or set to null, the key is unset/removed. |

#### Add External Image

Adds external image url with meta-information to the product variant.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `addExternalImage` |
| variantId | Integer | required. This is the variant id in this product. |
| image | ImageView | required |

#### Remove Image

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `removeImage` |
| variantId | Integer | required. This is the variant id in this product. |
| imageUrl | String | required.  The URL of the image. |

#### Set SearchKeywords

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setSearchKeywords` |
| searchKeywords | SearchKeywords | required |

#### Set Meta Title

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setMetaTitle` |
| metaTitle | LocalizedString | optional |

#### Set meta Description

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setMetaDescription` |
| metaDescription | LocalizedString | optional |

#### Set Meta Keywords

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `setMetaKeywords` |
| metaKeywords | LocalizedString | optional |

#### Revert Staged Changes

Revert all changes, which were made to the staged version of a product and reset to the current version.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `revertStagedChanges` |

#### Publish

Publishes a product, which causes the staged projection of the product to override the current projection. If the product is published for the first time, the current projection is created.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `publish` |

#### Unpublish

Unpublishes a product, effectively deleting the current projection of the product, leaving only the staged projection. Consequently, when a product is unpublished, it will no longer be included in query or search results issued with staged=false, since such results only include current projections.

| field name | field type | comments |
|---|---|---|
| action | String | required, set as `unpublish` |

## 3. API

### 3.1 create product

* URL: {ip}/products/
* method: POST
* request body:

| name | type | comments |
|----|-----|------|
| productDraft | ProductDraft | required |

* response: ProductView

### 3.2 get product by id

* URL: {ip}/products/{productId}
* method: GET
* path variable:

| name | type | comments |
|----|-----|----|
| productId | String | product id, required |

* response: ProductView

## 4. Event Consumer Design

### 4.1 Model Design


| type | comment |
|---|---|
| List\<String\> | list of category id |


### 4.2 Subscriptions

Topic name is: `reactivesw-category-deleted`

Subscription name is: `product-category-deleted`

Use gcloud command to create this topic:

```bash
gcloud beta pubsub subscriptions create --topic reactivesw-category-deleted product-category-deleted
```

### 4.3 Process

1. get category id list which is deleted.
2. remove those id in product data.