package io.reactivesw.product.infrastructure.validator

import io.reactivesw.exception.ConflictException
import io.reactivesw.product.domain.model.Product
import spock.lang.Specification

/**
 * Unit test for VersionValidator class.
 */
class VersionValidatorTest extends Specification {
    VersionValidator validator = new VersionValidator()

    def "Test1: version match"() {
        given:
        def version = 1
        Product entity = new Product(version: version)

        when:
        VersionValidator.validate(entity, version)

        then:
        true
    }

    def "Test2: version not match and throw ConflictException"() {
        given:
        def version = 2
        Product entity = new Product(version: 1)

        when:
        VersionValidator.validate(entity, version)

        then:
        thrown(ConflictException)
    }
}
