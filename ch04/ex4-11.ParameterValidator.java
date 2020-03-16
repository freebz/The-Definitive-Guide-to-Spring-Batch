// Listing 4-11. ParameterValidator That Validates the File Name is a .csv

...
public class ParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
	String fileName = parameters.getString("fileName");

	if(!StringUtils.hasText(fileName)) {
	    throw new JobParametersInvalidException("fileName parameter is missing");
	}
	else if(!StringUtils.endsWithIgnoreCase(fileName, "csv")) {
	    throw new JobParametersInvalidException("fileName parameter does " +
					"not use the csv file extension");
	}
    }
}
