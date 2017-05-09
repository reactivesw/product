package io.reactivesw.product.application.service.admin

import io.reactivesw.exception.ConflictException
import io.reactivesw.product.application.admin.service.ProductApplication
import io.reactivesw.product.application.service.ProductRestClient
import io.reactivesw.product.domain.model.Product
import io.reactivesw.product.domain.service.ProductService
import io.reactivesw.product.infrastructure.repository.ProductRepository
import io.reactivesw.product.infrastructure.update.UpdaterService
import spock.lang.Specification

/**
 * Test for productApplication
 */
class ProductApplicationTest extends Specification {
    def productService = Mock(ProductService)
    def productRestClient = Mock(ProductRestClient)
    def productRepository = Mock(ProductRepository)
    def updateService = Mock(UpdaterService)
    ProductApplication productApplication = new ProductApplication(productService, productRestClient, updateService)
    def product = new Product()
    def id = "111111"
    def version = 1

    def "Test1: delete product by id, and response is void"() {
        given: "prepare data"
        product.setVersion(version)
        productService.getProductById(_) >> product

        when: "call function to delete product"
        productApplication.deleteProductById(id, version)

        then: "response is void"
        true
    }

}
