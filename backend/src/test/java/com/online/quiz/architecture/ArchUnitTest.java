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
          .layer("Controller").definedBy("..controller..")
          .layer("Model").definedBy("..model..")
          .layer("Repository").definedBy("..repository..")
          .layer("Service").definedBy("..service..")
          .layer("Configuration").definedBy("..config..")
          .layer("Security").definedBy("..security..")

          .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
          .whereLayer("Model").mayOnlyBeAccessedByLayers("Repository", "Service", "Configuration", "Security")
          .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service", "Configuration", "Security")
          .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service", "Security");

  @ArchTest
  public static final ArchRule loggingLibraryShouldBeUsedInsteadOfSystemOut =
          GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
                  .because("The preferred way of logging is via a logging library like logback");

  @ArchTest
  public static final ArchRule noFieldInjection = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
