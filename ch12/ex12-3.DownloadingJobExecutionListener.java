// Listing 12-3. DownloadingJobExecutionListener.java

...
public class DownloadingJobExecutionListener extends JobExecutionListenerSupport {

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Value("${job.resource-path}")
    private String path;

    @Override
    public void beforeJob(JobExecution jobExecution) {

	try {
	    Resource[] resources =
		this.resourcePatternResolver.getResources(this.path);

	    StringBuilder paths = new StringBuilder();

	    for (Resource resource : resources) {

		File file = File.createTempFile("input", ".csv");

		StreamUtils.copy(resource.getInputStream(),
				        new FileOutputStream(file));

		paths.append(file.getAbsolutePath() + ",");
		System.out.println(">> downloaded file : " +
				file.getAbsolutePath());
	    }

	    jobExecution.getExecutionContext()
		                .put("localFiles",
				paths.substring(0, paths.length() - 1));
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
