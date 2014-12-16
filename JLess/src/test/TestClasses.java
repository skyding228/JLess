package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.wid.jless.json.JLessJson;
import org.wid.jless.ssist.Classes;
import org.wid.jless.ssist.JLessMethod;

import test.bean.Hello;

public class TestClasses {
	
	@Before
	public void Before(){
		
	}

	@Test
	public void testGetMethodParams() {
		List<JLessMethod> params = null;
		try {
			params = Classes.getMethodParamsList(Hello.class.getName(), "sayHello");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JLessJson.stringify(params));
		assertEquals(3, params.size());
		
	}

}
