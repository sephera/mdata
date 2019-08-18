package com.github.sephera.sample.infrastructure.config;

import com.github.sephera.mdata.autoconfigure.EnableMData;
import com.github.sephera.sample.infrastructure.respository.first.FirstEntity;

@EnableMData(basePackageClasses = FirstEntity.class, prefix = "first")
public class FirstDBConfig {
}
