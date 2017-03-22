# Product Service Rest API

## 1. Introduce

TODO

## 2. View Model

### 2.1 ProductProjectionView

| field name | field type | comments |
|----|----|----|
| id | String | |
| key | String | |
| productType | Reference | |
| name | LocalizedString | |
| description | LocalizedString | |
| slug | String | |
| categories | List<Reference> | |
| categoryOrderHints | List<CategoryOrderHintView> | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | | 
| metaKeywords | LocalizedString | |
| searchKeyword | List<SearchKeyword> | |
| ProductVariantView | masterVariant | |
| List<ProductVariantView> | variants | |


### 2.2 ProductView
| field name | field type | comments |
|----|----|----|
| id | String | |
| key | String | |
| productType | Reference | |
| name | LocalizedString | |
| slug | String | |
| categories | List<Reference> | |
| categoryOrderHints | List<CategoryOrderHintView> | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | | 
| metaKeywords | LocalizedString | |
| searchKeyword | List<SearchKeyword> | |
| ProductVariantView | masterVariant | |
| List<ProductVariantView> | variants | |

### 2.3 ProductVariantView

| field name | field type | comments |
|----|----|----|
| id | Integer | |
| sku | String | |
| key | String | |
| prices | List<PriceView> | |
| attributes | List<AttributeView> | | 
| price | PriceView | | 
| images | List<ImageView> | |
| available | boolean | |
| isMatchingVariant| boolean | |

### 2.4 PagedQueryResult

| field name | field type | comments | 
|-----|-----|-----|
| offset | Integer | |
| count | Integer | |
| total | Integer | |
| results | List<T> | |
| facets | Object | |


## 3. API

### 3.1 get product projection list by categoryId

* URL : /projections
* params : categoryId
* response : PagedQueryResult<ProductProjectionView>
    

### 3.2 get product detail by productId

* URL : /{productId}
* response : ProductView
* error code :
  
  | code | comments |
  |------|--------|
  | 404 | can not find product by this id |  