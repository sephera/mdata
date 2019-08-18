package com.github.sephera.mdata.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class MDataRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {

        final Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableMData.class.getName());
        assert attributes != null;
        final Class<?>[] packages = (Class<?>[]) attributes.get("basePackageClasses");
        final String prefix = (String) attributes.get("prefix");

        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(MDataSourceConfig.class);
        gbd.getPropertyValues().addPropertyValue("prefix", prefix);
        gbd.getPropertyValues().addPropertyValue("unitName", prefix + "Unit");
        gbd.getPropertyValues().addPropertyValue("packages", packages);
        registry.registerBeanDefinition(prefix + "MDataConfig", gbd);
    }
}
