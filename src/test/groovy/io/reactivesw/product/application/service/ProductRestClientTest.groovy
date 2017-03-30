package io.reactivesw.product.application.service

import com.google.common.collect.Lists
import io.reactivesw.product.application.model.InventoryEntryView
import io.reactivesw.product.application.model.ProductTypeView
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.xml.ws.Response

/**
 * Created by Davis on 17/3/29.
 */
class ProductRestClientTest extends Specification {

    RestTemplate restTemplate = Mock()
    def productTypeUri = "product-type-uri"
    def inventoryUri = "http://inventory/"
    ProductRestClient client = new ProductRestClient(restTemplate: restTemplate,
            productTypeUri: productTypeUri, inventoryUri: inventoryUri)

    def "Test 1: get product type by id"() {
        given:
        def productTypeId = "product-type-111"

        ProductTypeView productTypeView = new ProductTypeView()
        productTypeView.id = productTypeId
        restTemplate.getForObject(_, _) >> productTypeView

        when:
        def result = client.getProductType(productTypeId)

        then:
        result != null
        result.id == productTypeId
    }

    def "Test 2.1: get inventory by sku"() {
        given:
        def sku1 = "sku-111"
        def sku2 = "sku-222"
        List<String> skus = Lists.newArrayList(sku1, sku2)
        InventoryEntryView inventoryEntryView = new InventoryEntryView()
        inventoryEntryView.sku = sku1
        def inventoryEntryViews = [inventoryEntryView] as InventoryEntryView[];
        restTemplate.getForObject(_, _) >> inventoryEntryViews

        when:
        def result = client.getInventoryBySkus(skus)

        then:
        result != null
    }

    def "Test 2.2: get inventory by null sku and return empty result"() {
        given:
        List<String> skus = null

        when:
        def result = client.getInventoryBySkus(skus)

        then:
        result != null
        result.isEmpty()
    }

    def "Test 2.3: get inventory by empty sku and return empty result"() {
        given:
        List<String> skus = Lists.newArrayList()

        when:
        def result = client.getInventoryBySkus(skus)

        then:
        result != null
        result.isEmpty()
    }
}
