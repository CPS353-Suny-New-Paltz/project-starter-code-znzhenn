package integrationtests;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOutput {
    private final List<String> output;

    public InMemoryOutput() {
        this.output = new ArrayList<>();
    }

    public List<String> getOutput() {
        return output;
    }

    public void write(String s) {
        output.add(s);
    }
}
