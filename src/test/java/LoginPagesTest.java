import org.testng.annotations.Test;

import com.qa.hubspot.keywordEngine.KeywordEngine;

public class LoginPagesTest {
	
	@Test
	public void loginTest()
	{
		KeywordEngine ke = new KeywordEngine();
		ke.startExceution("login");
	}
}
