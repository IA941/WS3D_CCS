package br.com.cominotti.ws3d_ccs.application.world.use_cases.creation;

public class CreateWorldUseCaseInput {

    private final String host;

    private final Integer port;


    public CreateWorldUseCaseInput() {
        this("localhost", 4011);
    }

    public CreateWorldUseCaseInput(final String host, final Integer port) {
        this.host = host;
        this.port = port;
    }


    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }
}
