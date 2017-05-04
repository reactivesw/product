# Customer-Web Requirement on Product Service

## 1. Introduction

Product service is used to manage product: create new product, update product field, query product information, delete product etc.
The functions product service provides to as below:

1. get list of products by category.
2. get detail information by sku name.
3. get product information for cart.

## 2. Requirement

### 2.1 Basic requirement about product

A product should meet the following conditions:

1. A product can be associated to different category, and have different order in each category.
2. Product's name and description should be multi-language.
3. Product should have a master variant and other different variants, a variant is model which store the main information about product like price, attribute, image etc.
4. Product can be associated to only one producttype, which defines the attributes used with product.

### 2.2 Basic requirement about variant

A variant should meet the following conditions:

1. Variant should have an integer id, master variant id should be 1, and other variants id should be continuous numbers start with 2.
2. Variant should have list of price, and the first one is the main price.
3. Varaint should have list of image, and the first one is the main image.
4. Variant should hava unique sku name.
5. Variant should have list of attribute value, match on product type attribute definition.

### 2.3 Get List of Products by Category

Use category id to get list of products information.
Product information should be simple, just contains the master variant information, one price and one image is ok.

### 2.4 Get Detail Information by Sku Name

Use sku name to get product detail information.
Detail information means return producttype information and all variant information.

### 2.5 Get Product Information for Cart

Use product id and variant id to get variant information for cart service.
For cart service, return product name, sku name, variant id, images, main price is ok.

## 3. How to provide

Product service provides RESTful API , here is [api documentation](./customer_api.md)
