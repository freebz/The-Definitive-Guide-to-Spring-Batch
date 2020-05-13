// Listing 10-7 FieldSetMapper Configurations for the Customer Update File

...
@Bean
public FiledSetMapper<CustomerUpdate> customerUpdateFieldSetMapper() {
    return fieldSet -> {
	switch (filedSet.readInt("recordId")) {
	        case 1: return new CustomerNameUpdate(
		                fieldSet.readLong("customerId"),
				fieldSet.readString("firstName"),
				fieldSet.readString("middleName"),
				fieldSet.readString("lastName"));
	        case 2: return new CustomerAddressUpdate(
		                fieldSet.readLong("customerId"),
				fieldSet.readString("address1"),
				fieldSet.readString("address2"),
				fieldSet.readString("city"),
				fieldSet.readString("state"),
				fieldSet.readString("postalCode"));
	        case 3:
		        String rawPreference =
			        fieldSet.readString("notificationPreferences");

			Integer notificationPreferences = null;

			if(StringUtils.hasText(rawPreference)) {
			    notificationPreferences = Integer.parseInt(rawPreference);
			}

			return new CustomerContactUpdate(
			        fieldSet.readLong("customerId"),
				fieldSet.readString("emailAddress"),
				fieldSet.readString("homePhone"),
				fieldSet.readString("cellPhone"),
				fieldSet.readString("workPhone"),
				        notificationPreferences);

	        default: throw new IllegalArgumentException(
		        "Invalid record type was found:" +
			        fieldSet.readInt("recordId"));
	}
    };
}
...
