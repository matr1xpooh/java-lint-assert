package org.lint.azzert.command;

import org.lint.azzert.LintCommand;
import org.lint.azzert.context.Context;

public class ExemptDisabledClassesCommand implements LintCommand<Void> {
    @Override
    //remove all disabled tests
    public Void execute(final Context context){
        context.getMethods().removeIf(method -> method.getTestFramework().isDisabledClass(method));
        return null;
    }
}
