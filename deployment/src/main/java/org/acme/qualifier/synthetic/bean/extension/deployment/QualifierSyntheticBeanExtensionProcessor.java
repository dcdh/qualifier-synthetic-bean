package org.acme.qualifier.synthetic.bean.extension.deployment;

import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import jakarta.inject.Singleton;
import org.acme.qualifier.synthetic.bean.extension.runtime.HelloWorldService;
import org.acme.qualifier.synthetic.bean.extension.runtime.HelloWorldServiceRecorder;
import org.acme.qualifier.synthetic.bean.extension.runtime.Person;
import org.jboss.jandex.ClassType;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class QualifierSyntheticBeanExtensionProcessor {

    private static final String FEATURE = "qualifier-synthetic-bean-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    List<SyntheticBeanBuildItem> registerPersons(
            final ApplicationIndexBuildItem applicationIndexBuildItem,
            final HelloWorldServiceRecorder helloWorldServiceRecorder) {
        final Set<String> persons = applicationIndexBuildItem.getIndex()
                .getAnnotations(DotName.createSimple(Person.class))
                .stream()
                .map(qualifier -> qualifier.value("name").asString())
                .collect(Collectors.toSet());

        return persons.stream()
                .map(personName -> SyntheticBeanBuildItem.configure(HelloWorldService.class)
                        .scope(Singleton.class)
                        .createWith(helloWorldServiceRecorder.supplier(personName))
                        .addQualifier().annotation(DotName.createSimple(Person.class)).addValue("name", personName).done()
                        .unremovable()
                        .setRuntimeInit()
                        .done())
                .toList();
    }

}
