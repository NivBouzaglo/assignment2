package bgu.spl.mics.application.services;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.objects.Model;
import bgu.spl.mics.application.objects.Student;

/**
 * Student is responsible for sending the {@link TrainModelEvent},
 * {@link TestModelEvent} and {@link PublishResultsEvent}.
 * In addition, it must sign up for the conference publication broadcasts.
 * This class may not hold references for objects which it is not responsible for.
 * <p>
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class StudentService extends MicroService {
    private Student student;

    public StudentService(Student student) {
        super(student.getName());
        this.student = student;
        // TODO Implement this
    }

    @Override
    protected void initialize() {
        System.out.println("intilaize: " +this.getName());
        subscribeBroadcast(TerminateBroadcast.class, t -> {terminate();});
        subscribeBroadcast(PublishConferenceBroadcast.class, t -> {
            for (Model name : t.getModelsName()) {
                if (student.getModels().contains(name))
                    student.addPublication();
                else
                    student.addPapersRead();
            }
        });
        for (Model m : student.getModels()) {
            TrainModelEvent train = new TrainModelEvent(m);
            train.setFuture(sendEvent(train));
            train.action(train.getModel());
            if (m.getStatus().equals(Model.status.Trained)){
            TestModelEvent test = new TestModelEvent((Model) train.getFuture().get());
            Future future = sendEvent(test);
            test.action(test.getModel().getR());
            if (test.getModel().getStatus().equals("Tested")){
                PublishResultsEvent p = new PublishResultsEvent(m);
                sendEvent(p);
            }
        }}
    }

}
