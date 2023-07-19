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

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Constraint(Type type, String range, String value) {
  public enum Type {
    AGE,
    SEX
  }
}
