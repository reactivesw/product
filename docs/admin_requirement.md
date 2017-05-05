# Admin-Web Requirement on Product Service

## 1. Introduction

Product service is used to manage product, provides following functions:

* create product
* delete product
* update product
* get product detail information
* get all product

## 2. Requirement

### 2.1 Basic requirement about product

A product should meet the following conditions:

1. A product can be associated to different category, and have different order in each category.
2. Product's name and description should be multi-language.
3. Product should have a master variant and other different variants, a variant is model which store the main information about product like price, attribute, image etc.
4. Product can be associated to only one producttype, which defines the attributes used with product.
5. Product should have current status and stagged status, customer can only get current status information.

### 2.2 Basic requirement about variant

A variant should meet the following conditions:

1. Variant should have an integer id, master variant id should be 1, and other variants id should be continuous numbers start with 2.
2. Variant should have list of price, and the first one is the main price.
3. Varaint should have list of image, and the first one is the main image.
4. Variant should hava unique sku name.
5. Variant should have list of attribute value, match on product type attribute definition.

### 2.3 Create product

1. new product should match the rules described in 2.1 and 2.2.

### 2.4 Delete product

1. use product id and version to delete product, if version not match, can not success.

### 2.5 Update product

1. when update a product, update it's stagged information, when change the publish status to true, copy the stagged information into current information.

### 2.6 Get product detail information

1. use product id to get detail information.

### 2.7 Get all product

## 3. How to provide

Product service provides RESTful API, here is [api document](./admin_api.md)