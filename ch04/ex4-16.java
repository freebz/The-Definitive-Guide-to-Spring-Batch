// Listing 4-16. Using a JobParametersIncrementer in a Job

...
@Bean
public CompositeJobParametersValidator validator() {
    CompositeJobParametersValidator validator =
	         new CompositeJobParametersValidator();

    DefaultJobParametersValidator defaultJobParametersValidator =
	       new DefaultJobParametersValidator(
		              new String[] {"fileName"},
			      new String[] {"name", "run.id"});

    defaultJobParametersValidator.afterProfpertiesSet();

    validator.setValidators(
	         Arrays.asList(new ParameterValidator(),
		       defaultJobParametersValidator));

    return validator;
}

@Bean
public Job job() {
    return this.jobBuilderFactory.get("basicJob")
	         .start(step1())
	         .validator(validator())
	         .incrementer(new RunIdIncrementer())
	         .build();
}
...
