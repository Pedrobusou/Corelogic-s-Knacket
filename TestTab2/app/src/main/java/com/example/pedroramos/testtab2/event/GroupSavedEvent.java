package com.example.pedroramos.testtab2.event;

public class GroupSavedEvent {
    public static final String PARAM_CREATE = "create";
    public static final String PARAM_DELETE = "delete";
    public static final String PARAM_EDIT = "edit";
    String mode;

    public GroupSavedEvent(String mode) {
        this.mode = mode;
    }

    public GroupSavedEvent() {

    }

    public String getMode() {
        return mode;
    }
}
