package com.imaginnovate.employee.api.service;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import org.hibernate.MappingException;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.service.ServiceRegistry;


public class EmployeeGenerateSchema {
	
	public static void main(String[] args) throws MappingException, IOException {
		ServiceRegistry serviceRegistry = buildCfg();
		Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

		String outputFile = "src/main/resources/database/model.sql";
		FileChannel.open(Paths.get(outputFile), StandardOpenOption.WRITE).truncate(0).close();

		new SchemaExport().setOutputFile(outputFile).setDelimiter(";").create(EnumSet.of(TargetType.SCRIPT), metadata);
		metadata.buildSessionFactory().close();
		System.exit(0);
	}

	public static StandardServiceRegistryImpl buildCfg() {
		return (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	}

}
