package integrationtests;

import java.util.ArrayList;
import java.util.List;

public class InMemoryInput {
	private final List<Integer> integers;

	public InMemoryInput(List<Integer> integers) {
		this.integers = integers;
	}

	public List<Integer> getInput() {
		return integers;
	}



	public String getInputString() {
        return String.join(",", integers.stream()
                                        .map(String::valueOf)
                                        .toArray(String[]::new));
    }

}