package com.example.pedroramos.testtab2.event;

public class RecordingStoppedEvent {
    Boolean isSuccess;

    public RecordingStoppedEvent(Boolean isSuccess){
        this.isSuccess=isSuccess;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
