package com.cloud.disk;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.cloud.disk")
public class ArchRuleTest {

    @ArchTest
    public static final ArchRule architecture_layer_should_has_right_dependency =layeredArchitecture()
            .layer("Library").definedBy("..cloud.disk.library..")
            .layer("PlatForm").definedBy("..cloud.disk.platform..")
            .layer("FileBundle").definedBy("..cloud.disk.bundle.file..")
            .layer("DynamicBundle").definedBy("..cloud.disk.bundle.dynamic..")
            .layer("UserBundle").definedBy("..cloud.disk.bundle.user..")
            .layer("AllBundle").definedBy("..cloud.disk.bundle..")
            .layer("App").definedBy("..cloud.disk.app..")
            .whereLayer("App").mayOnlyBeAccessedByLayers()
            .whereLayer("FileBundle").mayOnlyBeAccessedByLayers("App")
            .whereLayer("DynamicBundle").mayOnlyBeAccessedByLayers("App")
            .whereLayer("UserBundle").mayOnlyBeAccessedByLayers("App")
            .whereLayer("PlatForm").mayOnlyBeAccessedByLayers("App","AllBundle")
            .whereLayer("Library").mayOnlyBeAccessedByLayers("App","AllBundle","PlatForm");
}
