package projectapis;

import project.annotations.NetworkAPIPrototype;

public class UserAPIPrototype {

    @NetworkAPIPrototype
    public static void prototype(UserAPI api) {
        // pretend client usage
        long factorialSum = api.fetchFactorialOfSum();
        long existingResult = api.fetchExistingResult();
    }
}
