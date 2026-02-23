import java.util.*;

public class OnboardingService {

    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repository;


    public OnboardingService(StudentInputParser parser, StudentValidator validator, StudentRepository repository) {

        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        Map<String, String> kv = parser.parse(raw);

        // validation inline, printing inline

        List<String> errors = validator.validate(kv);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");



        if (!errors.isEmpty()) {
            ConsoleReporter.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repository.save(rec);

        ConsoleReporter.printSuccess(id, rec, repository.count());
    }
}
