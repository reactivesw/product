# Product Design for Admin-Web

This document describes how to achieve the [requirement](./admin_requirement.md)

## 1. Basic Functions

Product service provides following functions:

* create product
* delete product
* update product
* get product detail information by id
* get all products

## 2. Model Design

For product, there is 5 special requirements:

* publish

  customer-web can get a product information only after published this product.

* current status and stagged status

  a product should have two status: current and stagged, customer-web can get current information only.

* multi-language

  product name, description should be multi-language

* attribute constraint

  product attribute value should match the constraint defined by product type.

* multi attribute value

  product attribute value can be multiple: string, number, money, boolean and so on.

### 2.1 Publish

In product model, we define a boolean field named `published`.
When create a product, `published` is false default. Customer-Web can not get product when it's `published` is false.

### 2.2 Current Status and Stagged Status

To achieve this requirement, we define 3 fields in product model:

* current

  This object store all information about a product, like name, description, variant, category etc.
  When change `staggedChanged` from `true` to `false`, copy `stagged` information into `current`.

* stagged

  This object store all information as the same as current.
  When update a product, update information in `stagged`, and change `staggedChanged` from `false` to `true`.

* staggedChanged

  This field means that `stagged` has been change.
  When change `staggedChanged` from `false` to `true`, means that update a product and change information in `stagged`.
  When change `staggedChanged` from `true` to `false`, means that approve this update in `stagged` and copy those into `current`.

### 2.2 Multi-language

In our system, multi-language is basic function, here is design [document](https://github.com/reactivesw/ecommerce-cloud/blob/master/docs/multilanguange-design.md).

### 2.3 Attribute Constraint

In product type, defines 4 kinds of attribute constraint:

* None -- no constrains between attributes.
* Unique -- all attributes should be unique.
* CombinationUnique -- when two or more attributes combines, the combined attribute shoule be unique.
* SameforAll -- all values in a attribute should be same.

Except the 4 constraint, there are 2 special constraint:

* when a attribute defined as `required`, the value should not be null.
* attribute defined by product type, must contain all attribute in product.

When create a product, must match above rule, here is validation rule:



### 2.4 Multi Attribute Value

## 3. Workflow

## 4. Event Design

