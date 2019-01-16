package org.ccjmne.orca.jooq.test;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.junit.Test;

public class TestGenerateJooqClasses {

  private static final String DB_DRIVER = "org.postgresql.Driver";

  private static final String DB_USER = System.getProperty("db_user", "postgres");
  private static final String DB_PASS = System.getProperty("db_pass", "postgrespwd");
  private static final String DB_URL  = String.format(
                                                      "jdbc:postgresql://%s:%s/%s",
                                                      System.getProperty("db_host", "localhost"),
                                                      System.getProperty("db_port", "5432"),
                                                      System.getProperty("db_name", "orcadb"));

  @Test
  public void generateJooqClasses() throws Exception {
    GenerationTool.generate(new Configuration()
        .withJdbc(
                  new Jdbc().withDriver(DB_DRIVER).withUrl(DB_URL).withUser(DB_USER).withPassword(DB_PASS))
        .withGenerator(
                       new Generator()
                           .withDatabase(
                                         new Database()
                                             .withName("org.jooq.meta.postgres.PostgresDatabase")
                                             .withIncludes(".*")
                                             .withExcludes("")
                                             .withInputSchema("public"))
                           .withTarget(new Target().withPackageName("org.ccjmne.orca.jooq.classes").withDirectory("src/main/java"))));
  }
}
