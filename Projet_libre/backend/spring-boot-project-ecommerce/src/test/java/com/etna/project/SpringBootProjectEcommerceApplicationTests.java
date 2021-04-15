package com.etna.project;

import org.junit.internal.TextListener;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SpringBootProjectEcommerceApplicationTests {

	@Test
	void contextLoads() {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(
				TestCountryController.class,
				TestProductController.class,
				TestProductCategoryController.class
		);

		resultReport(result);
	}

	public static void resultReport(Result result) {
		System.out.println("Finished. Result: Failures: " +
				result.getFailureCount() +". Ignored: "+
				result.getIgnoreCount() +". Tests run: "+
				result.getRunCount() +". Time: "+
				result.getRunTime() +"ms."
				);
	}
}