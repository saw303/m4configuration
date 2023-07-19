/*
 * Copyright by onstructive GmbH 2020 - 2023. All rights reserved.
 *
 * onstructive GmbH
 * Buckhauserstrasse 49
 * 8048 Zürich
 * Switzerland
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by Silvio Wangler <silvio.wangler@onstructive.ch>, January 2020.
 */
package io.wangler.micronaut.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ConfigurationProperties(ProductConfiguration.PREFIX)
public interface ProductConfiguration {

  String PREFIX = ProductEngineConfiguration.PREFIX + ".product-configuration";

  @NotBlank
  String getDefaultBaseInsuranceProduct();

  @NotBlank
  String getThirdKidDiscountCodeActive();

  @NotBlank
  String getThirdKidDiscountCodeInactive();

  @NotBlank
  String getFamilyDiscountCodeActive();

  @NotBlank
  String getFamilyDiscountCodeInactive();

  Set<Product> getProducts();

  @Introspected
  record Product(
      String name,
      InsuranceBeginStrategy insuranceBeginStrategy,
      LegalBasis legalBasis,
      List<Criterion> criteria,
      Constraint constraint) {}

  enum LegalBasis {
    KVG,
    VVG
  }

  enum InsuranceBeginStrategy {
    FIRST_DAY_OF_THE_FOLLOWING_YEAR,
    FIRST_DAY_OF_THE_FOLLOWING_YEAR_OR_NEXT_MONTH
  }

  @Introspected
  record Criterion(CriterionName name, Boolean exposable, Constraint constraint, String value) {
    public enum CriterionName {
      ACCIDENT,
      PREMIUM_REGION,
      DEDUCTIBLE,
      AGE_GROUP,
      SEX,
      INSURANCE_SUM_COMBINED,
      INSURANCE_SUM_INVALIDITY,
      INSURANCE_SUM_DEATH,
      PROGRESSION,
      DEDUCTIBLE_AMBULATORY,
      DEDUCTIBLE_STATIONARY
    }
  }

  default Optional<Product> findProductConfiguration(String productName) {
    return getProducts().stream().filter(p -> p.name().equals(productName)).findAny();
  }

  default List<Product> findProductConfigurations(Collection<String> productNames) {
    return getProducts().stream().filter(p -> productNames.contains(p.name())).toList();
  }
}
