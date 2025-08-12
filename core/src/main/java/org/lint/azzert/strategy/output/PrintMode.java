package org.lint.azzert.strategy.output;

import org.lint.azzert.LintCommand;
import org.lint.azzert.command.RemoveMethodsWithAssertsCommand;

public enum PrintMode {

    ASSERTLESS_ONLY(new RemoveMethodsWithAssertsCommand()), ALL(new LintCommand<Void>(){});

    private final LintCommand<Void> command;
    private PrintMode(LintCommand<Void> command){
        this.command = command;
    }

    public LintCommand<Void> getCommand(){
        return command;
    }


}
