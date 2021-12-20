package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.objects.Model;
//i added <T>
public class TrainModelEvent implements Event<Model> {
    private Model model ;

    public TrainModelEvent(Model m){
        model = m ;
    }
    public Model getModel() {
        return model;
    }
}
