package pl.com.michalpolak.hyperbudget.transaction.event;

import com.google.gson.Gson;

class EventData {

    private final String action;
    private final EventContext context;

    private EventData(String action, EventContext context) {
        this.action = action;
        this.context = context;
    }

    static EventData of(String action, EventContext context) {
        return new EventData(action, context);
    }

    public String getAction() {
        return action;
    }

    public EventContext getContext() {
        return context;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
