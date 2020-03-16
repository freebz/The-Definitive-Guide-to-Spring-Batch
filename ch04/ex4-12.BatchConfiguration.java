// Listing 4-12. DefaultJobParametersValidator Configuration in BatchConfiguration.java

...
@Bean
public JobParametersvalidator validator() {
    DefaultJobParametersValidator validator = new DefaultJobParametersValidator();

    validator.setRequiredKeys(new String[] {"fileName"});
    validator.setOptionalKeys(new String[] {"name"});

    return validator;
}
...
