package com.github.sephera.mdata.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;

public class MDataAutoSelector implements ImportSelector {
    private static final Logger LOG = LoggerFactory.getLogger(MDataAutoSelector.class);

    @PostConstruct
    public void init() {
        LOG.info("Initializing {}", MDataAutoSelector.class.getName());
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[0];
    }
}
