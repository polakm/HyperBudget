package pl.com.michalpolak.hyperbudget.transaction.event;

class EventData {
    private String action;

    private EventContext context;

    EventData(String action, EventContext context) {
        this.action = action;
        this.context = context;
    }

    public String getAction() {
        return action;
    }

    public EventContext getContext() {
        return context;
    }


}
