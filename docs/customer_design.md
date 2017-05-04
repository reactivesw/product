# Product Design for Customer-Web

This document describes how to achieve the customer-web [requirement](./customer_requirement.md)

## 1. Basic Functions

Product service provides following functions:

1. get list of products by category.
2. get detail information by sku name.
3. get product information for cart.

## 2. Model Design

For product, there is no special requirment, here is api document [reference](./customer_api.md).

## 3. Workflow

### 3.1 Get List of Products by Category

1. get all product from database.
2. filter product list by category id.
3. convert product list to CategoryProductView list.
4. get sku names, and get inventory by those sku names.
5. merge inventory into CategoryProductView.
6. return CategoryProductView result.

### 3.2 Get Detail Information by Sku Name

1. get product entity from database by sku name.
2. convert product entity to DetailProductView.
3. get product type by product type id.
4. merge product type into DetailProductView.
5. get sku names, and get inventory by those sku names.
6. merge inventory into DetailProductView.
7. return DetailProductView result.

### 3.3 Get Product Information for Cart

1. get product entity from database by id.
2. get product variant by variant id.
3. convert product and variant to CartProductView.
4. return CartProductView.