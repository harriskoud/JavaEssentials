package scheduledThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadClass {
		
	public static void main(String Args[]) {
		
		ScheduledExecutorService scheduledExecutorService =
		        Executors.newScheduledThreadPool(5);

		ScheduledFuture scheduledFuture =
		    scheduledExecutorService.schedule(new Callable() {
		        public Object call() throws Exception {
		            System.out.println("Executed!");
		            return "Called!";
		        }
		    },
		    2,
		    TimeUnit.SECONDS);
		
	}


	
}
