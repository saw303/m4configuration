package io.wangler.micronaut.config


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class ProductEngineConfigurationSpec extends Specification {

    @Inject
    ProductEngineConfiguration productEngineConfiguration

    @Inject
    InsuranceNeedConfiguration insuranceNeedConfiguration

    @Inject
    ProductConfiguration productConfiguration


    void "Verify configuration"() {

        expect:
        productEngineConfiguration

        and:
        productConfiguration.defaultBaseInsuranceProduct == 'PRD_GV_BTC'
    }

    void "Verify insurance need configuration"() {

        given:
        InsuranceNeedConfiguration.InsuranceNeed first = productEngineConfiguration.insuranceNeedConfiguration.insuranceNeeds.first()

        expect:
        productEngineConfiguration.insuranceNeedConfiguration.insuranceNeeds.size() == 1

        and:
        with(first) {
            key() == 'insuranceNeeds.best.price'
            products().size() == 4
        }

        and:
        first.products()*.name().toSet().size() == 4

        and:
        productEngineConfiguration.insuranceNeedConfiguration == insuranceNeedConfiguration
    }

    void "Verify product configuration"() {

        given:
        ProductConfiguration productConfiguration = productEngineConfiguration.productConfigurations

        expect:
        productConfiguration == this.productConfiguration

        and:
        productConfiguration.products.size() == 1
        productEngineConfiguration.productConfigurations.products.size() == productConfiguration.products.size()

        and:
        productConfiguration.products*.name().size() == 1

        and:
        with(productConfiguration.products.find { p -> p.name() == 'PRD_KA_KTI' }) {
            name() == 'PRD_KA_KTI'
            criteria().size() == 19

            with(criteria().first()) {
                name() == ProductConfiguration.Criterion.CriterionName.AGE_GROUP
                value() == 'COD_LA_B01'
                !exposable()
                constraint().type() == Constraint.Type.AGE
                constraint().range() == '0..0'
            }
        }
    }
}
