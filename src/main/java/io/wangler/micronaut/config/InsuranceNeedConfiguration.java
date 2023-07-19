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

import java.util.List;
import java.util.UUID;

@ConfigurationProperties(InsuranceNeedConfiguration.PREFIX)
public interface InsuranceNeedConfiguration {

  String PREFIX = ProductEngineConfiguration.PREFIX + ".insurance-needs";

  List<InsuranceNeed> getInsuranceNeeds();

  @Introspected
  record InsuranceNeed(UUID id, String key, List<Product> products) {}

  @Introspected
  record Product(String name, Constraint constraint) {}
}
