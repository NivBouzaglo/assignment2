package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.Future;
import bgu.spl.mics.application.objects.ConfrenceInformation;
import bgu.spl.mics.application.objects.Model;

public class PublishResultsEvent implements Event<ConfrenceInformation>{
    private Model model;
    private Future future=new Future();

    public PublishResultsEvent(Model model){
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
    public Future getFuture() {
        return future;
    }
    public void action(Future future){
        this.future.resolve(future.get());
    }
    public boolean goodOrBad(){
        return model.good();
    }
    public Class getType(){
        return PublishResultsEvent.class;
    }
}
