package com.avseredyuk.carrental.web.command.result;

/**
 * Created by lenfer on 2/2/17.
 */
public class CommandResult {
    public enum ActionType {
        FORWARD, REDIRECT
    }

    private final String result;
    private final ActionType type;

    public CommandResult(String result, ActionType type) {
        this.result = result;
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public ActionType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandResult result1 = (CommandResult) o;

        if (!getResult().equals(result1.getResult())) return false;
        return getType() == result1.getType();
    }

    @Override
    public int hashCode() {
        int result1 = getResult().hashCode();
        result1 = 31 * result1 + getType().hashCode();
        return result1;
    }
}
