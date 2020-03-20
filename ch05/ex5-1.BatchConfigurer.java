// Listing 5-1. The BatchConfiguere Interface

public interface BatchConfiguere {

    JobRepository getJobRepository() throws Exception;

    PlatformTransactionManager getTransactionManager() throws Exception;

    JobLauncher getJobLauncher() throws Exception;

    JobExplorer getJobExplorer() throws Exception;
}
