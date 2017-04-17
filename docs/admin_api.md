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
| categoryOrderHints | List<CategoryOrderHintView> | |
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