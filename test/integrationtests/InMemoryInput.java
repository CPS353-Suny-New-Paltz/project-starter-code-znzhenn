package integrationtests;

import java.util.List;

public class InMemoryInput {
    private final List<Integer> integers;

    public InMemoryInput(List<Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

	public List<Integer> getInput() {
		// TODO Auto-generated method stub
		return null;
	}
}
