package com.github.sephera.sample.infrastructure.config;

import com.github.sephera.mdata.autoconfigure.EnableMData;
import com.github.sephera.sample.infrastructure.respository.second.SecondEntity;

@EnableMData(basePackageClasses = SecondEntity.class, prefix = "second")
public class SecondDBConfig {
}
