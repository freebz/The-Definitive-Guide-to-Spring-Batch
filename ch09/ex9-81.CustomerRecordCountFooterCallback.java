// Listing 9-81. CustomerRecordCountFooterCallback

...
@Component
@Aspect
public class CustomerRecordCountFooterCallback implements FlatFileFooterCallback {

    private int itemsWrittenInCurrentFile = 0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
	writer.write("This file contains " +
		        itemsWrittenInCurrentFile + " items");
    }

    @Before("execution(* org.springframework.batch.item.file.FlatFileItemWriter.open(..))")
    public void resetCounter() {
	this.itemsWrittenInCurrentFile = 0;
    }

    @Before("execution(* org.springframework.batch.item.file.FlatFileItemWriter.write(..))")
    public void beforeWrite(JoinPoint joinPoint) {
	List<Customer> items = (List<Customer>) joinPoint.getArgs()[0];

	this.itemsWrittenInCurrentFile += items.size();
    }
}
