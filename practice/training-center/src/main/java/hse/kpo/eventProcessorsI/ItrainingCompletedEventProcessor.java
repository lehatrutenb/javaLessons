package hse.kpo.eventProcessorsI;

import hse.kpo.domains.Train;

public interface ItrainingCompletedEventProcessor {
    public void onTrainingCompletedEvent(Train train);
}
