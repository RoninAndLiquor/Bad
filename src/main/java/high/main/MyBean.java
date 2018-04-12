package high.main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		//args.length 等于0
		System.out.println();
		System.out.println("【      SpringBoot already success started!   】");
		System.out.println();
	}

}
