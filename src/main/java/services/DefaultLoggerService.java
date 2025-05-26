package services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultLoggerService implements LoggerService{

    @Override
    public void log(String message) {
        System.out.println("Default: " + message);
    }
}
