# Product Service Rest API

## 1. Introduce

TODO

## 2. View Model

### 2.1 ProductView
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
| masterVariant | ProductVariantView | |
| variants | List<ProductVariantView> | |

### 2.2 ProductVariantView

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

### 2.3 PagedQueryResult

| field name | field type | comments | 
|-----|-----|-----|
| offset | Integer | |
| count | Integer | |
| total | Integer | |
| results | List<T> | |
| facets | Object | |

### 2.4 QueryConditions

| field name | field type | comments | 
|-----|-----|-----|
| where | String | |

## 3. API

### 3.1 get product list by categoryId

* URL : /
* params : where - required
* response : PagedQueryResult<ProductView>
* URL example: {URL}?where=categoryId%3A%2285a2aade-d87e-4b54-8485-54f186f48ace%22

### 3.2 get product detail by productId

* URL : /{productId}
* response : ProductView
* error code :
  
  | code | comments |
  |------|--------|
  | 404 | can not find product by this id |  