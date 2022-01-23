package com.online.quiz.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.online.quiz", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitTest {
  @ArchTest
  static final ArchRule layeredArchitectureRule = layeredArchitecture()
          .layer("Controller").definedBy("..web..")
          .layer("Domain").definedBy("..domain..")
          .layer("Repository").definedBy("..repository..")
          .layer("Service").definedBy("..service..")
          .layer("Configuration").definedBy("..config..")

          .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
          .whereLayer("Domain").mayOnlyBeAccessedByLayers("Repository", "Service", "Configuration")
          .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service", "Configuration")
          .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service");

  @ArchTest
  public static final ArchRule loggingLibraryShouldBeUsedInsteadOfSystemOut =
          GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
                  .because("The preferred way of logging is via a logging library like logback");

  @ArchTest
  public static final ArchRule noFieldInjection = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
