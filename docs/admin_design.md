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

1. validate the reqiured attribute, if missing required attribute, can not create this product.
2. validate the attribute name, to find that if contains all attribute in product.
3. validate unique attribute.
4. validate combination unique attribute.
5. validate same for all attribute.

### 2.4 Multi Attribute Value

Attribute value can be multiple, like string, enum, date, money and so on.
To store different kinds of value, wo have to define the value as a json column.

## 3. Workflow

### 3.1 Create Product

1. get product draft object.
2. validate product type: if product type is null or id is blank, can not create this product.
3. get product type view by product type id, if product type view is null, can not create this product.
4. validate attribute constraint follow the rule described in 2.3.
5. validate sku name: In a product, sku name should be unique.
6. save this product.
7. save event data: whoe and when to create this product.
8. return this product.

### 3.2 Delete Product

1. get product id and version.
2. get product entity object by id.
3. check whether id correspond with correct version or not.
5. delete this product.
6. save event data: who and when to delete this product.

### 3.3 Update Product

1. get product id and update action list.
2. get product entity object by id.
3. check whether id correspond with correct version or not.
4. update product by action list, follow the rule described in 2.2.
5. save event data: who and when to update this product.
6. return this product.

### 3.4 Get Product Detail Information by Id

1. get product id.
2. get product entity object by id.
3. convert entity to view and return result.

### 3.5 Get All Products

1. get all products from database.
2. convert product entity object to view object.
3. put all view object into a `PageQueryObject` and count the size of view object.
4. return `PageQueryObject`.

## 4. Event Design

### 4.1 Event Consumer

#### 4.1.1 Model

#### 4.1.2 Subscription

* Topic name: `reactivesw-category-deleted`
* Subscription name: `product-category-deleted`

Use gcloud command to create the subscription:

```shell
gcloud beta pubsub subscriptions create --topic reactivesw-category-deleted product-category-deleted
```

#### 4.1.3 Workflow

1. get list of CategoryId from event message.
2. get product by those category id.
3. remove those category and it's orderhint from product.

### 4.2 Event Producer

In product service, should produce 3 events:

* create product event
* delete product event
* update product event

#### 4.2.1 Create Product Event

##### Model

* event data

| field name | field type | comment |
|-|-|-|
| customerId | String | the customer who create this product |
| createdTime | ZonedDatetime | the time created this product |
| productId | String | created product id |

##### Topic

Topic name: `reactivesw-product-created`

Use gcloud command to create the topic:

```shell
gcloud beta pubsub topics create reactivesw-product-created
```

##### Workflow

1. get list of event, when it's `Created` status or `Pending` status but created 1 minutes ago
2. convert list of event to event message
3. publish message
4. delete event

#### 4.2.2 Delete Product Event

* event data

| field name | field type | comment |
|-|-|-|
| customerId | String | the customer who deleted this product |
| createdTime | ZonedDatetime | the time deleted this product |
| productId | String | the product id |

##### Topic

Topic name: `reactivesw-product-deleted`

Use gcloud command to create the topic:

```shell
gcloud beta pubsub topics create reactivesw-product-deleted
```

##### Workflow

1. get list of event, when it's `Created` status or `Pending` status but created 1 minutes ago
2. convert list of event to event message
3. publish message
4. delete event

#### 4.2.3 Update Product Event

* event data

| field name | field type | comment |
|-|-|-|
| customerId | String | the customer who updated this product |
| createdTime | ZonedDatetime | the time updated this product |
| productId | String | the product id |
| actions | List\<String\> | update actions |

##### Topic

Topic name: `reactivesw-product-updated`

Use gcloud command to create the topic:

```shell
gcloud beta pubsub topics create reactivesw-product-updated
```

##### Workflow

1. get list of event, when it's `Created` status or `Pending` status but created 1 minutes ago
2. convert list of event to event message
3. publish message
4. delete event