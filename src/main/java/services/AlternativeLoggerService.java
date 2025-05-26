package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class AlternativeLoggerService implements LoggerService {
    @Override
    public void log(String message) {
        System.out.println("Alternative: " + message);
    }
}
