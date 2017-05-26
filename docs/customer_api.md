# Product Service Customer Rest API

## 1. Introduction

TODO

## 2. View Model

### 2.1 CategoryProductView
| field name | field type | comments |
|----|----|----|
| id | String | |
| name | LocalizedString | |
| sku | String | |
| price | PriceView | | 
| imageUrl | String | |
| available | boolean | |

### 2.2 DetailProductView
| field name | field type | comments |
|----|----|----|
| id | String | |
| productType | ProductType | |
| name | LocalizedString | |
| description | LocalizedString | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | | 
| metaKeywords | LocalizedString | |
| searchKeyword | List<SearchKeyword> | |
| masterVariant | ProductVariantView | |
| variants | List<ProductVariantView> | |

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

### 2.5 CartProductView

| field name | field type | comments | 
|-----|-----|-----|
| productId | String | | 
| name | LocalizedString | |
| variantId | Integer | | 
| sku | String | |
| images | List<ImageView> | | 
| price | PriceView | |


## 3. API

### 3.1 get CategoryProductView list by categoryId

* URL : /CategoryProducts
* params : categoryId - required
* response : PagedQueryResult<CategoryProductView>
* URL example: {URL}?categoryId=85a2aade-d87e-4b54-8485-54f186f48ace

### 3.2 get DetailProductView by Sku

* URL : /DetailProducts/{Sku}
* response : DetailProductView  
  
  
### 3.3 get CartProductView by productId
  
* URL : /CartProducts/{productId}
* response : CartProductView

### 3.4 search product by words

* URL: /search
* method: GET
* params: searchWords - required
* response: PagedQueryResult<CategoryProductView>
* URL example: {api-gateway url}/products/search?searchWords=test