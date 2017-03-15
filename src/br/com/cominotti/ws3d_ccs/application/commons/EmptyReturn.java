package br.com.cominotti.ws3d_ccs.application.commons;

public final class EmptyReturn {

    private static final EmptyReturn EMPTY_RETURN = new EmptyReturn();


    private EmptyReturn() {
        //
    }


    public static EmptyReturn get() {
        return EMPTY_RETURN;
    }
}
