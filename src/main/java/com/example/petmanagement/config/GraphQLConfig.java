package com.example.petmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLSchema graphQLSchema() {
        return new SchemaParserBuilder()
                .files("graphql/schema.graphqls")
                .resolvers(new QueryResolver(), new MutationResolver())
                .build()
                .makeExecutableSchema();
    }
}
