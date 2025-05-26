package services;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class LongTaskService {

    @Asynchronous
    public void performLongTask() {
        System.out.println("Started async");
        try {
            Thread.sleep(3000);
            System.out.println("Doing the task");
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Finished async! ");
    }
}
