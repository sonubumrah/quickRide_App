package com.sky.QuickRide.quickRide.App.exceptions;

public class RuntimeConflictAction extends  RuntimeException{
    public RuntimeConflictAction() {
    }

    public RuntimeConflictAction(String message) {
        super(message);
    }
}
