package usecases;

import lombok.Getter;
import lombok.Setter;
import services.LongTaskService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AsynBean implements Serializable{

    @Inject
    private LongTaskService longTaskService;

    private String message1;
    private String message2;

    @Getter @Setter
    private String taskName;

    public void startTask() {
        message1 = "Async action is in the logs";
        longTaskService.performLongTask();
    }

    public String getMessage() {
        return message1;
    }
}
