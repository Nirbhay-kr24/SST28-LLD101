public class Demo01 {

        public static void main(String[] args) {
            System.out.println("=== Student Onboarding ===");

            StudentRepository repository = new FakeDb();
            StudentInputParser parser = new StudentInputParser();
            StudentValidator validator = new StudentValidator();

            OnboardingService svc = new OnboardingService(parser, validator,repository);

            String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
            svc.registerFromRawInput(raw);

            System.out.println();
            System.out.println("-- DB DUMP --");
            System.out.print(TextTable.render3(repository));
        }
    }


