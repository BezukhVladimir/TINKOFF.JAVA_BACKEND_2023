package edu.project3.loganalyzer.configurator;

import edu.project3.loganalyzer.configurator.configurations.AbstractConfiguration;
import edu.project3.loganalyzer.configurator.parsers.NginxParser;

public final class Configurator {
    private Configurator() {
    }

    public enum ConfigurationType {
        NGINX
    }

//    public static AbstractConfiguration createConfiguration(String[] args) {
//        AbstractConfiguration configuration;
//
//        switch (// как-то узнаём, какой тип логов анализируем) {
//        case ConfigurationType.NGINX -> {
//            configuration = NginxParser.parse(args);
//        } ... другие виды логов
//    }
}
