package usecases;

import services.LoggerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoggingDemoBean {
    @Inject
    private LoggerService loggerService;

    public String logMessage() {
        loggerService.log("Showing @Alternative");
        return null;
    }
}
